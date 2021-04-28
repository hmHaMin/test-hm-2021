package test1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HandleData {
    public static final int MAX_WARRANTY = 100;
    public static final String FILENAME = "E:\\TrialTest\\input.txt";
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        //GenInput.CreateData();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String line = reader.readLine();
            Map<String, BufferedWriter> map = new HashMap<String, BufferedWriter>();
            while(line != null){
                String key = line.split(",")[4];
                if(map.get(key) == null) {
                    map.put(key,new BufferedWriter(new FileWriter(key +".txt")));
                }
                map.get(key).write(line +"\n");
                line = reader.readLine();
            }
            for(int i = 0; i <= MAX_WARRANTY; i++){
                if(map.get(String.valueOf(i)) != null){
                    map.get(String.valueOf(i)).close();
                }
            }
            reader.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}
