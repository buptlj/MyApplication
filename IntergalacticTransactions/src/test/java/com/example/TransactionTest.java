package com.example;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created by jie on 2017/2/24.
 */
public class TransactionTest {
    private Transaction ts;
    @org.junit.Before
    public void setUp() throws Exception {
        ts = new Transaction();
    }

    @org.junit.Test
    public void roman2arab() throws Exception {
        HashMap<String,Double> romanValue = new HashMap<>();
        Vector<String> src = new Vector<>();
        romanValue.put("glob",1.0);
        romanValue.put("prok",5.0);
        romanValue.put("pish",10.0);
        romanValue.put("tegj",50.0);
        src.add("pish");
        src.add("tegj");
        src.add("glob");
        //src.add("glob");
        int rst = 41;
        int rst1 = (int)ts.roman2arab(src,romanValue);
        assertEquals(rst,rst1,0);
    }

    @org.junit.Test
    public void getRomanValue() throws Exception {
        HashMap<String,Double> rst = new HashMap<>();
        Vector<String> src = new Vector<>();
        rst.put("glob",5.0);
        src.add("glob is V");
        HashMap<String,Double> rst1 = ts.getRomanValue(src);
        if (rst1.get("glob")==5.0)
            assertEquals(0,0,0);
        else
            assertEquals(1,0,0);
    }

    @org.junit.Test
    public void getMetalValue() throws Exception {
        HashMap<String,Double> romanValue = new HashMap<>();
        HashMap<String,Double> rst = new HashMap<>();
        HashMap<String,Double> rst1 = new HashMap<>();
        Vector<String> src = new Vector<>();
        romanValue.put("glob",1.0);
        romanValue.put("prok",5.0);
        romanValue.put("pish",10.0);
        romanValue.put("tegj",50.0);
        src.add("glob glob Silver is 34 Credits");
        src.add("glob prok Gold is 57800 Credits");
        src.add("pish pish Iron is 3910 Credits");
        //src.add("glob");
        rst.put("Silver",17.0);
        rst.put("Gold",14450.0);
        rst.put("Iron",195.5);
        rst1 = ts.getMetalValue(src,romanValue);
        assertEquals(rst.get("Silver"),rst1.get("Silver"),0);
    }

    @org.junit.Test
    public void getResult() throws Exception{
        HashMap<String,Double> romanValue = new HashMap<>();
        HashMap<String,Double> metalValue = new HashMap<>();
        Vector<String> rst = new Vector<>();
        Vector<String> rst1 = new Vector<>();
        Vector<String> src = new Vector<>();
        romanValue.put("glob",1.0);
        romanValue.put("prok",5.0);
        romanValue.put("pish",10.0);
        romanValue.put("tegj",50.0);
        metalValue.put("Silver",17.0);
        metalValue.put("Gold",14450.0);
        metalValue.put("Iron",195.5);
        src.add("how much is pish tegj glob glob ?");
        src.add("how many Credits is glob prok Silver ?");
        src.add("how many Credits is glob prok Gold ?");
        src.add("how many Credits is glob prok Iron ?");
        src.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        rst1 = ts.getResult(src,romanValue,metalValue);
        String msg = rst1.get(0);
        //src.add("glob");
    }

}