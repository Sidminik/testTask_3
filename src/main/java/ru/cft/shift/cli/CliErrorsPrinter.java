package ru.cft.shift.cli;

import java.util.List;

public class CliErrorsPrinter {
    private static final String ERROR_PREFIX = "CLI argument errors:";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrors(List<String> errors) {
        if (!errors.isEmpty()) {
            System.out.println(ERROR_PREFIX);
            errors.forEach(System.out::println);
        }
    }

    public static void printRawError(String error) {
        System.err.println(error);
    }
}
