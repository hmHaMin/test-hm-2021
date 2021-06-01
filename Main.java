/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author sonnv61
 */
public class Main {
    private static final String[] listCode = {"ERICVTTEK01","NOKIAGHTK02", "HUANET03", "APPHTEK04"};
    private static String[] listName = {"MSC ERICSSON", "EPC HUAWEI", "MSC NOKIA", "EPC VIETTEL"};
    private static String[] listfirstName = {" NgUyen", " LE", " PhAm", "trAn "};
    private static String[] listmidName = {" VAN "," THI ", " hoaNG ", "  HuY"};
    private static String[] listlastName = {" VAN "," THI ", " hoaNG ", "  HuY"};
    private static String[] listInputDate = {
            "23/11/2001",
            "16/03/2005",
            "11/11/2020",
            "17/09/2017",
            "09/12/2007",
            "05/07/2016",
            "21/12/2004"};
    public static String randomDevice(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        sb.append(listCode[random.nextInt(listCode.length)]).append(",")
                .append(listName[random.nextInt(listName.length)]).append(",")
                .append(listlastName[random.nextInt(listlastName.length)])
                .append(listmidName[random.nextInt(listmidName.length)])
                .append(listfirstName[random.nextInt(listfirstName.length)])
                .append(",")
                .append(listInputDate[random.nextInt(listInputDate.length)])
//                .append("10/11/1999")
                .append(",").append(random.nextInt(9) + 1).append("/n");
//        String code = listCode[random.nextInt(listCode.length)];
//        String name = listName[random.nextInt(listName.length)];
//        String owner = listlastName[random.nextInt(listlastName.length)]
//                + listmidName[random.nextInt(listmidName.length)]
//                + listfirstName[random.nextInt(listfirstName.length)];
//
//        String inputDate = "10/11/1999";
//        String warrentyYear = String.valueOf(random.nextInt(9) + 1);
//
//        return code + "," + name + "," + owner + "," + inputDate + "," + warrentyYear + "\n";
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //BufferedWriter bwi = new BufferedWriter(new FileWriter("etc\\input.txt"));
        StringBuilder sb = new StringBuilder();
        long startTime = System.currentTimeMillis();
        FileOutputStream fos = new FileOutputStream("D:\\HaPTH\\Test\\data\\input2.txt");
        Random random = new Random();
        for (int i = 0; i < 100000000; i++) {

            sb.append(listCode[random.nextInt(listCode.length)]).append(",")
                    .append(listName[random.nextInt(listName.length)]).append(",")
                    .append(listlastName[random.nextInt(listlastName.length)])
                    .append(listmidName[random.nextInt(listmidName.length)])
                    .append(listfirstName[random.nextInt(listfirstName.length)]).append(",")
                    .append("10/11/1999").append(",")
                    .append(random.nextInt(9) + 1).append("\n");
            //System.out.println(sb.toString());
            if(i % 1000 == 0){
                fos.write(sb.toString().getBytes(),0, sb.toString().length());
                sb.delete(0, sb.length());
               // sb = new StringBuilder();
            }

        }
        fos.write(sb.toString().getBytes(),0, sb.toString().length() );
        fos.close();
        //bwi.close();



//        BufferedReader br = new BufferedReader(new FileReader("etc\\input.txt"));
//
//        String line = "";
//            while (true) {
//                line = br.readLine();
//                if(line == null) {
//                    break;
//                }
//                WriteThread writeThread = WriteThreadFactory.getThreadPool(line.charAt(line.length()-1) - '0');
//                writeThread.push(line);
//            }
//            WriteThreadFactory.stopThread();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed );
    }


}


