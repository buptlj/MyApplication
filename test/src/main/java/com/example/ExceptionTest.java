package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jie on 2017/3/2.
 */

public class ExceptionTest {
    boolean testEx() throws Exception {
        boolean ret = true;
        try {
            testEx1();
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            return ret;
        }
    }

    void testEx1() throws Exception {
        boolean ret = true;
        try {
            testEx2();

            System.out.println("testEx1, at the end of try");

        } catch (Exception e) {
            System.out.println("testEx1, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx1, finally; return value=" + ret);
        }
    }

    boolean testEx2() throws Exception {
        boolean ret = true;
        try {
            int b = 12;
            int c;
            for (int i = 2; i >= -2; i--) {
                c = b / i;
                System.out.println("i=" + i);
            }
            return true;
        } catch (Exception e) {
            System.out.println("testEx2, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx2, finally; return value=" + ret);
            //return ret;
        }
    }
    static StringBuffer test0(){
        StringBuffer name = new StringBuffer("123");
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("catch name: " + name.hashCode());
            return name;
        }finally{
            name.append("456");
            name = new StringBuffer("abc");
            System.out.println("finally name: " + name.hashCode());
        }
    }
    public static Map<String, Object> method() {
        Map<String, Object> map = new HashMap<>();
        map.put("intValue1", 1);
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("catch:" + map);
            return map;
        } finally {
            map.put("intValue2", 2);
            map = new HashMap<>();
            map.put("intValue3",3);
            System.out.println("finally:" + map);
            return map;
        }

    }

}
