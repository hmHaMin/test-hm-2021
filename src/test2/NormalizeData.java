package test2;

import java.io.*;

public class NormalizeData implements Runnable{
    private String fileIn;
    private String fileOut;


    public NormalizeData(String fileIn, String fileOut) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    BufferedReader reader;
    BufferedWriter writer;
    String line;
    public void run() {
        try {
            System.out.println("Thread - " + Thread.currentThread().getId() +" is running !");
            reader = new BufferedReader(new FileReader(fileIn));
            writer = new BufferedWriter(new FileWriter(fileOut));
            while((line = reader.readLine())!= null){
                String[] part = line.split(",");
                String[] s = part[2].trim().replaceAll("\\s+", " ").split(" ");
//        for(String ss: s){
//            System.out.println(ss);
//        }
                String owner = "";
                for (int i = 0; i < s.length; i++) {
                    owner += s[i].substring(0, 1).toUpperCase() + s[i].substring(1).toLowerCase();
                    if (i < s.length - 1) owner += " ";
                }
                String str = part[0] + "," + part[1] + "," + owner + "," + part[3] + "," + part[4] + "\n";
                writer.write(str);
            }
            System.out.println("DONE!");
            reader.close();
            writer.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}