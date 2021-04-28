package test2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPool implements Runnable {
    private String year;
    private File file;
    BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();
    public ThreadPool(String year){
        this.file = new File("data\\input"+year+".txt");
    }

    public void push(String data) throws InterruptedException {
        this.blockingQueue.put(data);
    }
    BufferedWriter writer;
    @Override
    public void run() {
        try {
            String line;
            int i = 0;
            while ((line = blockingQueue.take()) != null) {
                writer = new BufferedWriter(new FileWriter(file));

                i++;
//                String[] part = line.split(",");
//                String[] s = part[2].trim().replaceAll("\\s+", " ").split(" ");
//                String owner = "";
//                for (int i = 0; i < s.length; i++) {
//                    owner += s[i].substring(0, 1).toUpperCase() + s[i].substring(1).toLowerCase();
//                    if (i < s.length - 1) owner += " ";
//                }
//                String str = part[0] + "," + part[1] + "," + owner + "," + part[3] + "," + part[4] + "\n";
//                writer.write(str);
                writer.write(line + "\n");
                if(i%100000 == 0) {
                    System.out.println(i);
                }
            }
        }
        catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
