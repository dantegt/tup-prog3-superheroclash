package ar.frbb.utn.tup;

import java.util.ArrayList;

public class Log {
    // 👺 Log Master
    private static ArrayList<String> logs = new ArrayList<>();
    public static ArrayList<String> it (String message) {
        logs.add(message);
        return logs;
    }
    public static ArrayList<String> list () {
        return logs;
    }
}
