package test2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreateData {
    static List<String> listCode = Arrays.asList("ERICVTTEK", "NOKIAVTTEK", "HUATELK", "APPVTTEK", "ERICNET");
    static List<String> listName = Arrays.asList("MSC ERICSSON", "EPC HUAWEI", "BTS NOKIA", "RNCVIETTEL", "EPC ERICSSON");
    static List<String> firstName = Arrays.asList("NgUyen   ", "LE ", "PhAm     ", "trAn ", "buI ", "CaO ", "vu     ", "Hoang       ");
    static List<String> midName = Arrays.asList("VAn    ", "dinH ", "thI ", "HuY     ", "QuOc ", "ThAnh ");
    static List<String> lastName = Arrays.asList("A", " b", " C", "d", "e", "G", "H", "m", "N", "Q", "k", "T", "L", "v", "s", "P");
    static List<String> inputDate = Arrays.asList("23/04/2021", "17/09/2009", "23/11/2003", "08/08/2019", "11/11/2020", "23/10/2010");
    public static final int NUM_OF_LINES = 10000000;
    public static final int NUM_OF_FILES = 10;
    public static final String FILENAME = "C:\\Users\\HP\\Documents\\Zalo Received Files\\test1\\test1\\data\\input.txt";
    public static Random random = new Random();
    public static StringBuilder builder = new StringBuilder();

    public CreateData() {
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));

        for(int i = 0; i < NUM_OF_FILES*NUM_OF_LINES; ++i) {
            writer.write(RandomData());
        }

        writer.close();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in seconds: " + timeElapsed / 1000L);
    }

    public static String RandomData() {
        if (builder.length() != 0) {
            builder.delete(0, builder.length());
        }

        builder.append((String)listCode.get(random.nextInt(listCode.size()))).append(random.nextInt(10)).append(random.nextInt(10) + 1)
                .append(",").append(listName.get(random.nextInt(listName.size())))
                .append(",").append(firstName.get(random.nextInt(firstName.size())))
                .append(midName.get(random.nextInt(midName.size()))).append(lastName.get(random.nextInt(lastName.size())))
                .append(",").append(inputDate.get(random.nextInt(inputDate.size())))
                .append(",").append(random.nextInt(20) + 1).append("\n");
        return builder.toString();
    }

    class CreateFileThreads implements Runnable {
        private String fileName;
        private long startTime;

        public CreateFileThreads(String fileName, long startTime) {
            this.fileName = fileName;
            this.startTime = startTime;
        }

        public void run() {
            try {
                System.out.println("Thread - " + Thread.currentThread().getId() + " is running");
                BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName, true));

                for(int i = 0; i < 10000000; ++i) {
                    writer.write(CreateData.RandomData()+ "\n");
                }

                writer.close();
            } catch (IOException var5) {
                var5.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - this.startTime;
            System.out.println("Execution time in seconds: " + timeElapsed / 1000L);
        }
    }
}