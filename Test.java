/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnv61
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();
//        Main.create();
        CreateData.createData();
        long end = System.currentTimeMillis();
        System.out.println("Create data time: " + (end - startTime));
        //handles
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\HaPTH\\Test\\data\\input.txt"));
            String line;
            String wrt;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                i++;
                ThreadPool thread = ThreadPoolFactory.getThreadPool(line.charAt(line.length()-1) - '0');
                thread.push(line);
                if (i % 1000000 == 0) {
                    System.out.println(i);
                }
            }
            ThreadPoolFactory.stopThread();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("Handle Data: " + (endTime1 - end));
        //merge file using NIO
//        FileOutputStream fos = new FileOutputStream("D:\\HaPTH\\Test\\data\\output.txt");
//        FileChannel fosC = fos.getChannel();
//        for (int j = 9; j >= 1; j--) {
//            FileInputStream fis = new FileInputStream("D:\\HaPTH\\Test\\data\\output" + j + ".txt");
//            FileChannel fisC = fis.getChannel();
//            ByteBuffer buffer = ByteBuffer.allocateDirect((int) fisC.size());
//            fisC.read(buffer);
//            fisC.close();
//            fis.close();
//            buffer.rewind();
//            fosC.write(buffer);
//        }
//        fosC.close();
//        fos.close();
        ///////

        //merge Files:
//        try{
//            FileOutputStream fos = new FileOutputStream(new File("D:\\HaPTH\\Test\\data\\output.txt"));
//            StringBuilder sb = new StringBuilder();
//            String line ;
//            BufferedReader read;
//            for(int i = 9; i >= 1; i--){
//                read = new BufferedReader(new FileReader("D:\\HaPTH\\Test\\data\\output"+ i + ".txt"));
//                int j = 0;
//                while((line = read.readLine())!= null){
//                    j++;
//                    sb.append(line).append("\n");
//                    if(j % 600 == 0){
//                        fos.write(sb.toString().getBytes());
//                        sb = new StringBuilder();
//                    }
//                }
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //Merge files
        OutputStream out = new FileOutputStream("D:\\HaPTH\\Test\\data\\output.txt");
        byte[] buff = new byte[1024000];
        for (int i = 9; i >= 1; i-- ) {
            InputStream in = new FileInputStream("D:\\HaPTH\\Test\\data\\output" + i + ".txt");
            int b = 0;
            while ( (b = in.read(buff)) >= 0)
                out.write(buff, 0, b);
            in.close();
        }
        out.close();
        long endTime2 = System.currentTimeMillis();
        System.out.println("Merge files: " + (endTime2 - endTime1) );
        System.out.println("Total time: " + (endTime2 - startTime) );
    }

}
