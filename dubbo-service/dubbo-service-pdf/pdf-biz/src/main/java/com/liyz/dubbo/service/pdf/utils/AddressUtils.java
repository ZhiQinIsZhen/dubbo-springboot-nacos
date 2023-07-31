package com.liyz.dubbo.service.pdf.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/2/11 10:48
 */
@UtilityClass
@Slf4j
public class AddressUtils {
    private static final Object LOCK = new Object();
    private static volatile Map<String, String> areaMap = null;
    private static Type mapType = new TypeReference<Map<String, String>>() {
    }.getType();

    private static void initAreaMap() {
        Map<String, String> areaMapLocal = areaMap;
        if (areaMapLocal == null) {
            synchronized (LOCK) {
                areaMapLocal = areaMap;
                if (areaMapLocal == null) {
                    try {
                        areaMapLocal = JSON.parseObject(new ClassPathResource("META-INF/area.json").getInputStream(), mapType);
                    } catch (IOException e) {
                        log.warn("初始化地址Map失败", e);
                        areaMapLocal = Maps.newHashMap();
                    }
                    areaMap = areaMapLocal;
                }
            }
        }
    }

    /**
     * https://boss.qjdchina.com/leads/common/getCountryList
     * 输出最新城市的字符串
     *
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {

//        System.out.println(convertToDetailAddress("东安路888弄","31","3101","310104"));
        Map<String, String> map = Maps.newLinkedHashMap();
        File file = new ClassPathResource("META-INF/area_data.json").getFile();
        String str = FileUtils.readFileToString(file, Charset.defaultCharset());
        JSONObject object = JSON.parseObject(str);
        JSONArray arrayS = JSON.parseArray(object.getString("data"));
        for (int i = 0; i < arrayS.size(); i++) {
            JSONObject p = arrayS.getJSONObject(i);
            String pcode = p.getString("code");
            String pname = p.getString("name");
            map.put(pcode, pname);
            JSONArray pcitys = p.getJSONArray("citys");
            for (int i1 = 0; i1 < pcitys.size(); i1++) {

                JSONObject c = pcitys.getJSONObject(i1);
                String ccode = c.getString("code");
                String cname = c.getString("name");
                map.put(ccode, cname);
                JSONArray careas = c.getJSONArray("areas");
                for (int i2 = 0; i2 < careas.size(); i2++) {

                    JSONObject a = careas.getJSONObject(i2);
                    String acode = a.getString("code");
                    String aname = a.getString("name");
                    map.put(acode, aname);
                }
            }
        }
        try {
            Path tempFile = Files.createTempFile("area_data_", ".json");
            Files.write(tempFile,
                    new Jackson2ObjectMapperBuilder().createXmlMapper(false).build().writerWithDefaultPrettyPrinter().writeValueAsBytes(map));
            System.out.println(tempFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static String convertToDetailAddress(String address, String... codes) {
        initAreaMap();
        StringBuilder str = new StringBuilder(100);
        if (codes != null) {
            for (String code : codes) {
                if (StringUtils.isNotBlank(code)) {
                    str.append(areaMap.getOrDefault(code, code));
                }
            }
        }
        String reStr = str.append(address).toString();
        if ("null".equals(reStr)) {
            return "--";
        }
        return reStr;
    }

    public static String appendAddress(String... names) {
        StringBuilder str = new StringBuilder(100);
        if (names != null) {
            for (String name : names) {
                if (StringUtils.isNotBlank(name)) {
                    str.append(name);
                }
            }
        }
        String reStr = str.toString();
        if ("null".equals(reStr)) {
            return "--";
        }
        return reStr;
    }
}
