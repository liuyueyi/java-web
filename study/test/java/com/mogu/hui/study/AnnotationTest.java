package com.mogu.hui.study;

import com.mogu.hui.study.annotation.CacheAnnotation;
import com.mogu.hui.study.annotation.Role;
import com.mogu.hui.study.base.BaseUser;
import com.mogu.hui.study.base.Cell;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yihui on 16/1/9.
 */
@CacheAnnotation(expire = 230)
public class AnnotationTest {
    private final static Logger logger = LoggerFactory.getLogger(AnnotationTest.class);
    @CacheAnnotation(expire = 60)
    Cell cell;

    @Role
    BaseUser baseUser;

    @Before
    public void setUp() {
        cell = new Cell();

        baseUser = new BaseUser();
    }

    @CacheAnnotation(expire = 250)
    public String anoMethod() {
        return "ano method";
    }

    /**
     * 类被注解时候的用法
     */
    @Test
    public void classAnoTest() {
        if(AnnotationTest.class.isAnnotationPresent(CacheAnnotation.class)) {
            CacheAnnotation annotation = AnnotationTest.class.getAnnotation(CacheAnnotation.class);
            String key = cell.getCell(2000);
            int expire = annotation.expire();
            logger.info("The key:{}  expire:{} ", key, expire);
        } else {
            logger.info("no annotation!");
        }
    }

    /**
     * 成员变量被注解时的用法
     */
    @Test
    public void fieldAnoTest() {
        Class<AnnotationTest> clz = AnnotationTest.class;
        Field[] fields = clz.getDeclaredFields();
        List<Field> result = new ArrayList<Field>();
        for (Field field:fields){
            if(field.getAnnotation(CacheAnnotation.class)!=null)
                result.add(field);
        }

        for(Field list:result){
            System.out.println("被注解的字段为:"+list.getName());
        }

        Class<Cell> cz = (Class<Cell>) cell.getClass();
        Field[] f = cz.getFields();
        logger.info("asdf");
    }

    @Test
    public void methodAnoTest() throws NoSuchMethodException {
        Class<AnnotationTest> clz = AnnotationTest.class;
        Method method = clz.getMethod("anoMethod", new Class[]{});
        if (method.isAnnotationPresent(CacheAnnotation.class)) {
            CacheAnnotation ano = method.getAnnotation(CacheAnnotation.class);
            int expire = ano.expire();
            System.out.println(expire);
        } else {
            System.out.println("not checked!");
        }
    }

    @Test
    public void roleTest() {
        if (baseUser.getClass().isAnnotationPresent(Role.class)) {
            Role role = baseUser.getClass().getAnnotation(Role.class);
            String name = role.level().getRoleName();
            System.out.println(name);
        }
        System.out.println(baseUser.generateName());

    }
}
