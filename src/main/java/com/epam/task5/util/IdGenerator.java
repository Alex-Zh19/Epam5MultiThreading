package com.epam.task5.util;

public class IdGenerator {
    private static int vanId = 0;
    private static int terminalId = 0;

    public static int generateId() {
        return vanId++;
    }

    public static int generateTerminalId(){
        return terminalId++;
    }
}
