package ar.frbb.utn.tup;

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


}
