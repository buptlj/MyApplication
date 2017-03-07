package com.example;

import java.io.*;

/**
 * Created by jie on 2017/3/6.
 */

public class IOTest {
    public void readFileByByte(String filename){
        File file = new File(filename);
        String ss = new String();
        byte[] bytes = new byte[1024];
        FileInputStream fileInput = null;
        try {
            if (!file.exists())
                file.createNewFile();
            fileInput = new FileInputStream(file);
            int bytereader =0;
            bytereader = fileInput.read(bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void readCharConsole() throws IOException {
        System.out.println("input your string,input q to exit");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bfr = new BufferedReader(isr);
        char c;
        do {
            c = (char)bfr.read();
            System.out.println(c);
        }while (c!='q');
    }
    public void readStringConsole() throws IOException {
        System.out.println("input your string,input q to exit");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bfr = new BufferedReader(isr);
        String ss = bfr.readLine();
        while (!ss.equals("end")){
            System.out.println(ss);
        }
    }

}
