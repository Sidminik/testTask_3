package ru.cft.shift.cli;

import ru.cft.shift.config.StatisticsMode;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.regex.Matcher;

public final class ArgsParser {
    static final String TXT_EXTENSION = ".txt";

    private static final String SHORT_STAT_ARG = "-s";
    private static final String FULL_STAT_ARG = "-f";
    private static final String OUTPUT_ARG = "-o";
    private static final String PREFIX_ARG = "-p";
    private static final String APPEND_ARG = "-a";

    private final List<String> commandReaderNames;
    private StatisticsMode commandStatistics;
    private String commandWriterOutputPath;
    private String commandWriterOutputPrefix;
    private boolean commandWriterAppend;

    private final List<String> errors;

    public ArgsParser(){
        this.commandReaderNames = new ArrayList<>();
        this.commandStatistics = StatisticsMode.NONE;
        this.commandWriterOutputPath = null;
        this.commandWriterOutputPrefix = null;
        this.commandWriterAppend = false;
        this.errors = new ArrayList<>();
    };

    public void parse (String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].endsWith(TXT_EXTENSION)) {
                commandReaderNames.add(args[i]);
            } else if (args[i].equals(SHORT_STAT_ARG)) {
                if (commandStatistics == StatisticsMode.NONE) {
                    commandStatistics = StatisticsMode.SHORT;
                }
            } else if (args[i].equals(FULL_STAT_ARG)) {
                commandStatistics = StatisticsMode.FULL;
            } else if (args[i].equals(OUTPUT_ARG)) {
                if (i + 1 >= args.length || args[i + 1].startsWith("-")) {
                    errors.add("Output path is missing after " + OUTPUT_ARG);
                } else {
                    String normalizedSeparator = args[i + 1].replaceAll("[/\\\\]",
                            Matcher.quoteReplacement(File.separator));
                    commandWriterOutputPath = normalizedSeparator;
                    i++;
                }
            } else if (args[i].equals(PREFIX_ARG)) {
                if (i + 1 >= args.length || args[i + 1].startsWith("-")) {
                    errors.add("Prefix is missing after " + PREFIX_ARG);
                } else {
                    commandWriterOutputPrefix = args[i + 1];
                    i++;
                }
            } else if (args[i].equals(APPEND_ARG)) {
                commandWriterAppend = true;
            } else if (i == 0 || (!args[i - 1].equals(OUTPUT_ARG) && !args[i - 1].equals(PREFIX_ARG))) {
                errors.add("Unknown argument: " + args[i]);
            }
        }
    }

    public List<String> getCommandReaderNames() {
        return commandReaderNames;
    }

    public boolean hasCommandStatistics() {
        return commandStatistics != StatisticsMode.NONE;
    }

    public StatisticsMode getCommandStatistics() {
        return commandStatistics;
    }

    public String getCommandWriterOutputPath() {
        return commandWriterOutputPath;
    }

    public String getCommandWriterOutputPrefix() {
        return commandWriterOutputPrefix;
    }

    public boolean isCommandWriterAppend() {
        return commandWriterAppend;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }
}
