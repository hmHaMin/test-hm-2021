/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sonnv61
 */
public class ThreadPoolFactory {
    private ThreadPoolFactory(){};
    static Map<Integer, ThreadPool> threadPoolType = new HashMap<>();// store list thread theo nam tuong ung
    // check xem thread da co hay chua, chua co thi tao va start
    public static ThreadPool getThreadPool(int year){
        if(threadPoolType.get(year) == null){
            threadPoolType.put(year, new ThreadPool(year));
            Thread t = new Thread(threadPoolType.get(year));
            t.start();
        }
        return threadPoolType.get(year);
    }
    public static void stopThread(){
        for(Map.Entry<Integer, ThreadPool> entry: threadPoolType.entrySet()){
            System.out.println(entry.getKey() + "is done");
            ThreadPool threadPool = entry.getValue();
            threadPool.stop();
        }
    }
}
