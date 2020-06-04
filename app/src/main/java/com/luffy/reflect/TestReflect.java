package com.luffy.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestReflect {
    public static void main(String[] args) {

        Student student = new Student("李四");

        try {
            Class mClass1 = student.getClass();
            Method mMethod1 = mClass1.getMethod("run");
            mMethod1.invoke(student);

            Class mClass2 = student.getClass();
            Method mMethod2 = mClass2.getMethod("speak", String.class);
            mMethod2.invoke(student, "hh");


            Class mClass3 = student.getClass();
            Method mMethod3 = mClass3.getMethod("intro", String.class, String.class);
            String[] strings = (String[]) mMethod3.invoke(student, "张三", "20");
            System.out.println(Arrays.toString(strings));

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
