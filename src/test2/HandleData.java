package test2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandleData {
    public static final int MAX_WARRANTY = 20;
    public static final String FILENAME = "C:\\Users\\HP\\Documents\\Zalo Received Files\\test1\\test1\\data\\input.txt";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String line;
            Map<String, BufferedWriter> map = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                Product product = Product.getProduct(line);
                if (map.get(product.getWarrantyYear()) == null) {
                    map.put(product.getWarrantyYear(), new BufferedWriter(new FileWriter("data\\" + product.getWarrantyYear() + ".txt")));
                }
                map.get(product.getWarrantyYear()).write(line +"\n");
            }
            for(int i = 0; i <= MAX_WARRANTY; i++){
                if(map.get(String.valueOf(i)) != null){
                    map.get(String.valueOf(i)).close();
                }
            }
            ExecutorService executor = Executors.newCachedThreadPool();
            for(int i = 1; i <= MAX_WARRANTY; i++){
                Thread t = new Thread(new NormalizeData("data\\"+i+".txt", "data\\" +i+"o.txt"));
                executor.execute(t);
            }
            executor.shutdown();
            while (!executor.isTerminated()){}

            BufferedWriter outFile = new BufferedWriter(new FileWriter("data\\output.txt"));
            BufferedReader reader1;
            //1
            for(int i = 1; i <= MAX_WARRANTY; i++){
                reader1 = new BufferedReader(new FileReader("data\\" + i+ ".txt"));
                while ((line = reader1.readLine()) != null){
                    outFile.write(line+"\n");
                }
            }
            outFile.write("DONE1####################################!");
            //2
            for(int i = MAX_WARRANTY; i >0; i--){
                reader1 = new BufferedReader(new FileReader("data\\" + i+"o.txt"));
                while ((line = reader1.readLine())!= null){
                    outFile.write(line+"\n");
                }
                reader1.close();
            }
            outFile.close();
        } catch (FileNotFoundException var11) {
            var11.printStackTrace();
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in seconds: " + timeElapsed / 1000);
    }

    public static String writeOut(String data) {
        String[] part = data.split(",");
        String[] s = part[2].trim().replaceAll("\\s+", " ").split(" ");
        String owner = "";
        for (int i = 0; i < s.length; i++) {
            owner += s[i].substring(0, 1).toUpperCase() + s[i].substring(1).toLowerCase();
            if (i < s.length - 1) owner += " ";
        }
        return part[0] + "," + part[1] + "," + owner + "," + part[3] + "," + part[4] + "\n";
    }
}

