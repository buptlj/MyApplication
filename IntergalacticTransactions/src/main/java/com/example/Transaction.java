package com.example;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Transaction {

    public static String userdir = System.getProperty("user.dir")+"\\IntergalacticTransactions\\";
    public static String fileName = userdir+"input.txt"; //文件名字


    public static Vector<String> readByBufferedReader(String fileName) {
        Vector<String> sentences = new Vector<>();
        try {
            File file = new File(fileName);
            // 读取文件，并且以utf-8的形式写出去
            if(!file.exists()||file.isDirectory())
                throw new FileNotFoundException();
            BufferedReader bufread;
            String read;
            bufread = new BufferedReader(new FileReader(file));
            while ((read = bufread.readLine()) != null) {
                //System.out.println(read);
                sentences.add(read);
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sentences;
    }

    public static boolean isExist(double diff){
        double[] diffSet = {4.0,9.0,40.0,90.0,400.0,900.0};
        for (int i=0;i<6;i++){
            if (diff==diffSet[i])
                return true;
        }
        return false;
    }

    public static double roman2arab(Vector<String> romanNum,HashMap<String,Double> romanValue){
        Double result = 0.0;
        int romanNumSize = romanNum.size();
        for (int i=0;i<romanNumSize;i++){
            if (romanNumSize-i==1)
                result+=romanValue.get(romanNum.get(i));
            else{
                if (isExist(romanValue.get(romanNum.get(i+1))-romanValue.get(romanNum.get(i)))) {
                    result += romanValue.get(romanNum.get(i + 1)) - romanValue.get(romanNum.get(i));
                    i++;
                }
                else {
                    result+=romanValue.get(romanNum.get(i));
                }
            }
        }
        return result;
    }

    public static HashMap<String,Double> getRomanValue(Vector<String> sentences){
        HashMap<String,Double> romanValue = new HashMap<>();
        for (int i=0;i<sentences.size();i++){
            String currentLine = sentences.get(i);
            String[] words = currentLine.split(" ");
            if (words[1].equals("is")){
                switch (words[2]){
                    case "I":
                        romanValue.put(words[0],1.0);
                        break;
                    case "V":
                        romanValue.put(words[0],5.0);
                        break;
                    case "X":
                        romanValue.put(words[0],10.0);
                        break;
                    case "L":
                        romanValue.put(words[0],50.0);
                        break;
                    case "C":
                        romanValue.put(words[0],100.0);
                        break;
                    case "D":
                        romanValue.put(words[0],500.0);
                        break;
                    case "M":
                        romanValue.put(words[0],1000.0);
                        break;
                    default:
                        System.out.println("I have no idea what you are talking about");
                        break;
                }
            }
        }
        return romanValue;
    }

    public static int isLoc(String[] words){
        for (int i=0;i<words.length;i++){
            if (words[i].equals("is"))
                return i;
        }
        return 0;
    }

    public static HashMap<String,Double> getMetalValue(Vector<String> sentences,HashMap<String,Double> romanValue){
        HashMap<String,Double> metalValue = new HashMap<>();
        for (int i=0;i<sentences.size();i++){
            String currentLine = sentences.get(i);
            String[] words = currentLine.split(" ");
            int wordsLength = words.length;
            if (words[wordsLength-1].equals("Credits")){
                int isLocation = isLoc(words);
                Vector<String> romanNum = new Vector<>();
                for (int j=0;j<isLocation-1;j++){
                    romanNum.add(words[j]);
                }
                double arabNum = roman2arab(romanNum,romanValue);
                double price = Double.parseDouble(words[isLocation+1]);
                metalValue.put(words[isLocation-1],price/arabNum);
            }
        }
        return metalValue;
    }

    public static Vector<String> getResult(Vector<String> sentences,HashMap<String,Double> romanValue,HashMap<String,Double> metalValue){
        Vector<String> result = new Vector<>();
        for (int i=0;i<sentences.size();i++){
            String currentLine = sentences.get(i);
            String[] words = currentLine.split(" ");
            int wordsLength = words.length;
            if (words[0].equals("how")&&words[1].equals("much")){
                int isLocation = isLoc(words);
                Vector<String> romanNum = new Vector<>();
                String msg = "";
                if (isLocation==0)
                    msg+="I have no idea what you are talking about";
                else {
                    for (int j=isLocation+1;j<wordsLength-1;j++){
                        romanNum.add(words[j]);
                        msg+=words[j]+" ";
                    }
                    double arabNum = roman2arab(romanNum,romanValue);
                    msg+="is "+Double.toString(arabNum);
                }
                result.add(msg);
            }
            if (words[0].equals("how")&&words[1].equals("many")){
                int isLocation = isLoc(words);
                Vector<String> romanNum = new Vector<>();
                String msg = "";
                if (isLocation==0)
                    msg+="I have no idea what you are talking about";
                else {
                    for (int j=isLocation+1;j<wordsLength-2;j++){
                        romanNum.add(words[j]);
                        msg+=words[j]+" ";
                    }
                    double arabNum = roman2arab(romanNum,romanValue);
                    int price = 0;
                    if (words[wordsLength-2].equals("Silver"))
                        price = (int)(arabNum*metalValue.get("Silver"));
                    if (words[wordsLength-2].equals("Gold"))
                        price = (int)(arabNum*metalValue.get("Gold"));
                    if (words[wordsLength-2].equals("Iron"))
                        price = (int)(arabNum*metalValue.get("Iron"));
                    msg+=words[wordsLength-2]+" is "+Integer.toString(price)+" Credits";
                }
                result.add(msg);
            }
        }
        return result;
    }

    public static void resultOutput(Vector<String> result){
        try {
            String content = "";
            for (int i=0;i<result.size();i++){
                content+=result.get(i)+"\n";
            }
            File file = new File(userdir+"output.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Vector<String> inputSentences = readByBufferedReader(fileName);
        HashMap<String,Double> romanValue = getRomanValue(inputSentences);
        HashMap<String,Double> metalValue = getMetalValue(inputSentences,romanValue);
        Vector<String> result = getResult(inputSentences,romanValue,metalValue);
        resultOutput(result);
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }

}
