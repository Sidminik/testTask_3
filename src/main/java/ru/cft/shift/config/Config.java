package ru.cft.shift.config;

import java.util.List;

public class Config {
    private final List<String> inputFiles;
    private final OutputOptions outputOptions;
    private final StatisticsMode statisticsMode;

    public Config(List<String> inputFiles, OutputOptions outputOptions, StatisticsMode statisticsMode) {
        this.inputFiles = inputFiles;
        this.outputOptions = outputOptions;
        this.statisticsMode = statisticsMode;
    }

    public List<String> getInputFiles() { return inputFiles; }

    public OutputOptions getOutputOptions() { return outputOptions; }

    public StatisticsMode getStatisticsMode() { return statisticsMode; }
}
