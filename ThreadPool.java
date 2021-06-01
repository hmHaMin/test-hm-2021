/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hapt
 */
public class ThreadPool implements Runnable {

    int wrtYear;
    File file;
//    BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
    ConcurrentLinkedQueue<String> blockingQueue = new ConcurrentLinkedQueue<>();// hang doi chua data de xuly
    public ThreadPool(int wrtYear) {
        this.file = new File("data\\output" + wrtYear + ".txt");
    }

    public void push(String data) {
        blockingQueue.add(data);
    }

    public void stop() {
        blockingQueue.add("STOP");
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getId());
            StringBuffer sb = new StringBuffer();
            String line;
            FileOutputStream fos = new FileOutputStream(file);
            int i = 0;
            while (true) {
                i++;
                line = blockingQueue.poll();
                if (line == null) {
                    continue;
                }
                if (line == "STOP") {
                    break;
                }
                sb.append(nomalizeOwner(line));
                if(i % 600 == 0){
                    fos.write(sb.toString().getBytes());
                    sb = new StringBuffer();
                }
            }
            fos.write(sb.toString().getBytes(),0, sb.toString().length());
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public String NormalizeData(String line) {
        String[] part = line.split(",");
        String str = part[0] + " " + part[1] + " " + ownerNormalize(part[2]) + " " + part[3] + " " + part[4] + "\n";
        return str;
    }
    static StringBuilder builder = new StringBuilder();
    public static String ownerNormalize(String str){
        String owner = "";
//        if(builder.length()!=0){
//            builder.delete(0, builder.length());
//        }
        String[] s = str.trim().toLowerCase().replaceAll("\\s+", " ").split(" ");
        for (int i = 0; i < s.length; i++) {
//            builder.append(s[i].substring(0,1).toUpperCase()).append(s[i].substring(1));
            owner += s[i].substring(0, 1).toUpperCase() + s[i].substring(1).toLowerCase();
            if (i < s.length - 1) {
//                builder.append(" ");
                owner += " ";
            }
        }


        return owner;
    }
    public static String nomalizeOwner(String line) {
        String[] elements = line.split(",");
        String oldOwner = elements[2];
        String newOwnn = oldOwner.toLowerCase().trim().replaceAll(" +", " ");
        return elements[0] + "," + elements[1] + "," + capitalizeString(newOwnn) + "," + elements[3] + ","
                + elements[4] + "\n";

    }

    public static String capitalizeString(String string) {
        char[] chars = string.toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i])) {
                found = false;
            }
        }
        return String.valueOf(chars);
    }
    ///

    private static String format(String s) {
        if (builder.length() != 0)
            builder.delete(0, builder.length());
        int dem = 0;
        boolean count = true;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ','){
                dem++;
                builder.append(s.charAt(i));
                int luu = i;
                while (dem == 2){
                    i++;
                    if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                        if(s.charAt(i-1) == ' ' || s.charAt(i-1) == ','){
                            builder.append((char)(s.charAt(i) - 32));
                            count = true;
                        }
                        else {
                            builder.append(s.charAt(i));
                            count = true;
                        }
                    }
                    else if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                        if(s.charAt(i-1) == ' ' || s.charAt(i-1) == ','){
                            builder.append(s.charAt(i));
                            count = true;
                        }else{
                            builder.append((char)(s.charAt(i)+32));
                            count = true;
                        }
                    }
                    else if(s.charAt(i) == ' '){
                        if(i == luu+1){
                            count = false;
                        }
                        if(count == true){
                            builder.append(' ');
                            count = false;
                        }
                    } else{
                        builder.append(s.charAt(i));
                        dem++;
                    }
                }
            }
            else builder.append(s.charAt(i));
        }
        builder.append("\n");
        return builder.toString();
    }

}
