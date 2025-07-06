package ru.cft.shift.cli;

import java.util.List;
import java.util.ArrayList;

import static ru.cft.shift.cli.ArgsParser.TXT_EXTENSION;

public class ArgsValidator {
    private final List<String> errors;
    private final CliErrorsPrinter consolePrinter;

    public ArgsValidator() {
        this.errors = new ArrayList<>();
        this.consolePrinter = new CliErrorsPrinter();
    }

    public void validateInputArgs(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Arguments array cannot be null. " +
                    "You must enter the file name. " +
                    "It is also possible to enter other arguments.");
        }
    }

    public void validate(ArgsParser argsParser) {
        validateReaderNames(argsParser.getCommandReaderNames());
        if (argsParser.hasErrors()) {
            errors.addAll(argsParser.getErrors());
        }
        consolePrinter.printErrors(errors);
        validateFileNamesNotEmpty(argsParser.getCommandReaderNames());
    }

    public void validateReaderNames(List<String> fileNames) {
        for (int i = 0; i < fileNames.size(); i++) {
            if (fileNames.get(i).endsWith(TXT_EXTENSION)) {
                if (fileNames.get(i).length() <= TXT_EXTENSION.length()) {
                    errors.add("Invalid file name: " + fileNames.get(i));
                    fileNames.remove(fileNames.get(i));
                }
            }
        }
    }

    public void validateFileNamesNotEmpty (List<String> fileNames) {
        if (fileNames.isEmpty()) {
            throw new IllegalArgumentException("The name of the incoming file(s) is missing. " +
                    "You must enter at least one name in the *.txt format.");
        }
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }
}
