package com.liyz.dubbo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/1 19:52
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestUtil.class})
public class TestUtilTest {

    @Test
    public void test() {
        log.info("random code : {}", RandomUtil.randomInteger(6));
    }


    @Test
    public void test1() {
        TestUtil.Wrapper a = new TestUtil.Wrapper<List<String>>();
        getType(new TestUtil.Wrapper<List<String>>());
        TestUtil.Wrapper b = new TestUtil.Wrapper<List<String>>() {
            List<Integer> list = new ArrayList<>();
        };
        Object v = new Object(){};
        getType(new TestUtil.Wrapper<List<String>>() {});
        getType(new TestUtil.Wrapper<String>() {});
        getType(new Date() {});
    }

    public static void getType(Object obj) {
        if (obj instanceof ParameterizedType) {
            log.info("type : {}", obj);
            Type[] types = ((ParameterizedType) obj).getActualTypeArguments();
            Arrays.stream(types).forEach(type -> getType(type));
        } else {
            Type type = obj.getClass().getGenericSuperclass();
            if (type == null) {
                return;
            }
            if (type.getTypeName().equals(Object.class.getTypeName())) {
                log.info("type : {}", obj);
                log.info("type : {}", type);
                return;
            }
            getType(type);
        }
    }

    @Test
    public void test2() {
        int a = 2;

        log.info("{}", Integer.TYPE.isPrimitive());
        log.info("{}", Double.TYPE.isPrimitive());
    }

    @Test
    public void test3() {
        String name = "乐视控股(北京）有限公司";
        log.info("{}", name.replaceAll("\\(", "（").replaceAll("\\)", "）"));
    }
}