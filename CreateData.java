/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.*;
import java.util.Random;

/**
 * @author sonnv61
 */
public class CreateData {
    private static String[] listCode = {"ERICVTTEK01","NOKIAGHTK02", "HUANET03", "APPHTEK04"};
    private static String[] listName = {"MSC ERICSSON", "EPC HUAWEI", "MSC NOKIA", "EPC VIETTEL"};
    private static String[] listfirstName = {" NgUyen", " LE", "  PhAm", "   trAn   "};
    private static String[] listmidName = {" Thu "," THI  ", " Thanh  ", "  HuY"};
    private static String[] listlastName = {" A"," b","   C"," d"};
    private static String[] listInputDate = {
            "23/11/2001",
            "16/03/2005",
            "11/11/2020",
            "17/09/2017",
            "09/12/2007",
            "05/07/2016",
            "21/12/2004"};
    private static final int MAX = 100000000;

    public static void main(String[] args) throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();
        FileOutputStream os = new FileOutputStream("D:\\HaPTH\\Test\\data\\input.txt");
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (long i = 0; i < MAX; i++) {
            builder.append(listCode[random.nextInt(listCode.length)])
                    .append(",").append(listName[random.nextInt(listName.length)])
                    .append(",").append(listfirstName[random.nextInt(listfirstName.length)])
                    .append(listmidName[random.nextInt(listmidName.length)])
                    .append(listlastName[random.nextInt(listlastName.length)])
                    .append(",")
                    .append(listInputDate[random.nextInt(listInputDate.length)])
                    .append(",").append(random.nextInt(9) + 1)
                    .append("\n");
            if (i % 600 == 0) {
                os.write(builder.toString().getBytes());
                builder.delete(0, builder.length());
            }
            if(i%1000000 == 0){
                System.out.println(i);
            }
        }
        os.write(builder.toString().getBytes());
        os.close();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
    public static void createData() throws IOException {
        long startTime = System.currentTimeMillis();
        FileOutputStream os = new FileOutputStream("D:\\HaPTH\\Test\\data\\input.txt");
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (long i = 0; i < MAX; i++) {
            builder.append(listCode[random.nextInt(listCode.length)])
                    .append(",").append(listName[random.nextInt(listName.length)])
                    .append(",").append(listfirstName[random.nextInt(listfirstName.length)])
                    .append(listmidName[random.nextInt(listmidName.length)])
                    .append(listlastName[random.nextInt(listlastName.length)])
                    .append(",")
                    .append(listInputDate[random.nextInt(listInputDate.length)])
                    .append(",").append(random.nextInt(9) + 1)
                    .append("\n");
            if (i % 1000 == 0) {
                os.write(builder.toString().getBytes());
                builder.delete(0, builder.length());
            }
            if(i%1000000 == 0){
                System.out.println(i);
            }
        }
        os.write(builder.toString().getBytes());
        os.close();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
}

