package ru.cft.shift.cli;

import ru.cft.shift.config.Config;
import ru.cft.shift.config.OutputOptions;

public class MapperArgsToConfig {

    private MapperArgsToConfig() {}

    public static Config map (ArgsParser commandLine) {
        return new Config(
                commandLine.getCommandReaderNames(),
                mapOutputConfig(commandLine),
                commandLine.getCommandStatistics()
        );
    }

    private static OutputOptions mapOutputConfig(ArgsParser commandLine) {
        return new OutputOptions(
                commandLine.getCommandWriterOutputPath(),
                commandLine.getCommandWriterOutputPrefix(),
                commandLine.isCommandWriterAppend()
        );
    }
}
