package test2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Main {
    public static final String FILENAME = "C:\\Users\\HP\\Documents\\Zalo Received Files\\test1\\test1\\data\\input.txt";
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String line;
            String wrtYear;
            while ((line = reader.readLine())!= null){
                wrtYear = line.split(",")[4].trim();
                ThreadPool thread = ThreadPoolFactory.getThread(wrtYear);
                thread.push(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreadPoolFactory {
    private ThreadPoolFactory(){}
    static Map<String, ThreadPool> threadPoolType = new HashMap<>();
    // check xem da co Thread chua, co roi thi tra ve thread do, con khong thi start
    public static ThreadPool getThread(String year){
        if(threadPoolType.get(year) == null){
            threadPoolType.put(year, new ThreadPool(year));
            Thread t = new Thread(threadPoolType.get(year));
            t.start();
        }
        return threadPoolType.get(year);
    }
}

