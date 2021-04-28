package test1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class CreateData {
    private static int NUM_OF_LINES = 100000000;
    public static final String FILENAME = "E:\\TrialTest\\input.txt";
    public static Random random = new Random();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
            Random random = new Random();
            String s;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < NUM_OF_LINES; i++) {
                if (builder.length() != 0) {
                    builder.delete(0, builder.length());
                }
//                builder.append(ListRandom.listCode1[random.nextInt(ListRandom.listCode1.length)])
//                        .append(ListRandom.listCode2[random.nextInt(ListRandom.listCode2.length)])
//                        .append(ListRandom.listCode3[random.nextInt(ListRandom.listCode3.length)])
//                        .append(",")
//                        .append(ListRandom.listName1[random.nextInt(ListRandom.listName1.length)])
//                        .append(ListRandom.listName2[random.nextInt(ListRandom.listName2.length)])
//                        .append(",")
//                        .append(ListRandom.firstName[random.nextInt(ListRandom.firstName.length)])
//                        .append(ListRandom.midName[random.nextInt(ListRandom.midName.length)])
//                        .append(ListRandom.lastName[random.nextInt(ListRandom.lastName.length)])
//                        .append(",")
//                        .append(ListRandom.inputDate[random.nextInt(ListRandom.inputDate.length)])
//                        .append(String.valueOf(random.nextInt(20)+ 1))
//                        .append("\n");
                builder.append(ListRandom.listCode1.get(random.nextInt(ListRandom.listCode1.size())))
                        .append(ListRandom.listCode2.get(random.nextInt(ListRandom.listCode2.size())))
                        .append(ListRandom.listCode3.get(random.nextInt(ListRandom.listCode3.size())))
                        .append(ListRandom.listName1.get(random.nextInt(ListRandom.listName1.size())))
                        .append(ListRandom.listName2.get(random.nextInt(ListRandom.listName2.size())))
                        .append(ListRandom.firstName.get(random.nextInt(ListRandom.firstName.size())))
                        .append(ListRandom.midName.get(random.nextInt(ListRandom.midName.size())))
                        .append(ListRandom.lastName.get(random.nextInt(ListRandom.lastName.size()))).append(",")
                        .append(ListRandom.inputDate.get(random.nextInt(ListRandom.inputDate.size()))).append(",")
                        .append(random.nextInt(20) + 1).append("\n");
                s = builder.toString();
                writer.write(s);

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }

    public static String InputDate() {
        int dd = random.nextInt(30) + 1;
        int mm = random.nextInt(12) + 1;
        int yyyy = random.nextInt(22) + 2000;
        String date = "";
        if (dd > 28 && mm == 2) dd = 28;
        date = intConvertToString(dd) + "/" + intConvertToString(mm) + "/" + String.valueOf(yyyy);
        return date;
    }

    public static String intConvertToString(int value) {
        if (value < 10) return "0" + String.valueOf(value);
        else return String.valueOf(value);
    }
}
