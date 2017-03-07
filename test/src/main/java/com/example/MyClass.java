package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {

    public static void main(String[] args){
        String filedir = System.getProperty("user.dir")+"\\test\\";
        String fileName = filedir+"input.txt";
        //BufferedInputStream bf;
        IOTest ioTest = new IOTest();
        try {
            ioTest.readCharConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
