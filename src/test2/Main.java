package test2;

import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;

public class ThreadFactory {
    private ThreadFactory(){}
    static Map<String, ThreadPool> map = new HashMap<>();
    // check xem da co Thread chua, co roi thi tra ve thread do, con khong thi start
    public static ThreadPool getThread(String year){
        if(map.get(year) == null){
            map.put(year, new ThreadPool(year));
            Thread t = new Thread(map.get(year));
            t.start();
        }
        return map.get(year);
    }
}
