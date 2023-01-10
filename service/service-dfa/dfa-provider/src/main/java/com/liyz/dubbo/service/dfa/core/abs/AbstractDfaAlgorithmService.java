package com.liyz.dubbo.service.dfa.core.abs;

import com.liyz.dubbo.service.dfa.core.DfaAlgorithmService;
import com.liyz.dubbo.service.dfa.exception.DfaExceptionCodeEnum;
import com.liyz.dubbo.service.dfa.exception.RemoteDfaServiceException;
import com.liyz.dubbo.service.dfa.model.DfaWordDO;
import com.liyz.dubbo.service.dfa.service.IDfaWordService;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/15 11:13
 */
public abstract class AbstractDfaAlgorithmService implements DfaAlgorithmService, ApplicationListener<ContextRefreshedEvent> {

    /**
     * char '#'  表示结束标志
     */
    private static final Character END_CHAR = Character.valueOf('#');

    @Resource
    private IDfaWordService dfaWordService;

    /**
     * 敏感词容器
     * key: 敏感词的char
     * value: 敏感词的替换后的字符 "" or null: 表示全部替换为*; 否则替换为目标字符
     */
    @Getter
    private Map<Character, Object> wordMap;

    @Override
    public String dfa(String sourceWord) {
        if (StringUtils.isBlank(sourceWord) || CollectionUtils.isEmpty(wordMap)) {
            return sourceWord;
        }
        return doDfa(sourceWord);
    }

    protected String doDfa(String sourceWord) {
        StringBuilder sb = new StringBuilder();
        int beginIndex = -1;
        Map<Character, Object> lastMap = wordMap;
        Map<Character, Object> currentMap = wordMap;
        for (int i = 0; i < sourceWord.length(); i++) {
            beginIndex = doDfaAlgorithm(sourceWord, i, beginIndex, lastMap, currentMap, sb);
        }
        return sb.toString();
    }

    private int doDfaAlgorithm(String sourceWord, int index, int beginIndex, Map<Character, Object> lastMap,
                        Map<Character, Object> currentMap, StringBuilder sb) {
        char keyChar = sourceWord.charAt(index);

        if (!needCompare(keyChar)) {
            //不需要比较
            sb = beginIndex >= 0 ? sb.append(getEndValue(lastMap.get(END_CHAR), sourceWord, index, beginIndex)) : sb.append(keyChar);
            lastMap = wordMap;
            currentMap = wordMap;
            return -1;
        } else {
            //需要比较
            Object childMap = currentMap.get(keyChar);
            if (beginIndex < 0) {
                if (Objects.isNull(childMap)) {
                    sb = sb.append(keyChar);
                    currentMap = wordMap;
                    return -1;
                } else {
                    lastMap = currentMap;
                    currentMap = (Map<Character, Object>) childMap;
                    return index;
                }
            } else {
                if (Objects.isNull(childMap)) {
                    sb = sb.append(getEndValue(lastMap.get(END_CHAR), sourceWord, index, beginIndex));
                    lastMap = wordMap;
                    currentMap = wordMap;
                    return -1;
                } else {
                    currentMap = (Map<Character, Object>) childMap;
                    if (index == (sourceWord.length() - 1)) {
                        sb = sb.append(getEndValue(lastMap.get(END_CHAR), sourceWord, index, beginIndex));
                    } else {
                        lastMap = currentMap;
                    }
                    return beginIndex;
                }
            }
        }
    }

    /**
     * 获取结束标志的字符
     *
     * @param endValue
     * @param sourceWord
     * @param index
     * @param beginIndex
     * @return
     */
    private String getEndValue(Object endValue, String sourceWord, int index, int beginIndex) {
        if (Objects.isNull(endValue) || StringUtils.isBlank(endValue.toString())) {
            return sourceWord.substring(beginIndex, index);
        }
        return endValue.toString();
    }

    /**
     * 比较敏感词是否在该范围内
     *
     * @param keyChar
     * @return
     */
    private static boolean needCompare(char keyChar) {
        if ((keyChar >= 'a'&& keyChar <= 'z') || (keyChar >= 'A'&& keyChar <= 'Z') || (keyChar >= '\u4e00' && keyChar <= '\u9fa5')) {
            return true;
        }
        return false;
    }

    /**
     * 敏感词库初始化
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<DfaWordDO> list = dfaWordService.list();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        wordMap = new HashMap<>(list.size());
        for (DfaWordDO wordDO : list) {
            if (StringUtils.isNotBlank(wordDO.getSourceWord())) {
                initWord(wordDO, wordMap, 0, wordDO.getSourceWord().length());
            }
        }
    }

    /**
     * 初始化敏感词
     *
     * @param wordDO
     * @param parentMap
     * @param index
     * @param length
     */
    private void initWord(DfaWordDO wordDO, Map<Character, Object> parentMap, int index, int length) {
        String sourceWord = wordDO.getSourceWord();
        if (StringUtils.isNotBlank(sourceWord)) {
            char keyChar = sourceWord.charAt(index);
            if (keyChar == END_CHAR.charValue()) {
                throw new RemoteDfaServiceException(DfaExceptionCodeEnum.HAS_END_CHAR);
            }
            Map<Character, Object> childMap = getChildMap(keyChar, parentMap);
            if (index < length - 1) {
                initWord(wordDO, childMap, index + 1, length);
            } else {
                childMap.put(END_CHAR, wordDO.getTargetWord());
            }
        }
    }

    /**
     * 根据字符获取子map
     *
     * @param keyChar
     * @param parentMap
     * @return
     */
    private Map<Character, Object> getChildMap(char keyChar, Map<Character, Object> parentMap) {
        Object childMap = parentMap.get(keyChar);
        if (Objects.isNull(childMap)) {
            childMap = new HashMap<Character, Objects>();
            ((Map<Character, Object>) childMap).put(END_CHAR, null);
            parentMap.put(keyChar, childMap);
        }
        return (Map<Character, Object>) childMap;
    }
}
