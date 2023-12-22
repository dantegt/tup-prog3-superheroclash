package ar.frbb.utn.tup;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Log {
    // 👺 Log Master
    public static ArrayList<String> logs = new ArrayList<>();
    public static void it (String message) {
        logs.add(message);
    }
    public static ArrayList<String> list () {
        return logs;
    }
    public static void reset () {
        logs = new ArrayList<>();
    }
    public static void exportLog() throws IOException {
        FileWriter writer = new FileWriter("logDeBatalla.txt");
        for(String log: logs) {
            writer.write(log + System.lineSeparator());
        }
        writer.close();
    }

}
