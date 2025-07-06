package ru.cft.shift;

import ru.cft.shift.cli.ArgsParser;
import ru.cft.shift.cli.ArgsValidator;
import ru.cft.shift.core.DataProcessingService;
import ru.cft.shift.core.file.TxtFileReader;
import ru.cft.shift.core.file.TxtFileParser;
import ru.cft.shift.core.file.TxtFileWriter;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        DataProcessingService dataService = new DataProcessingService(
                new ArgsParser(),
                new ArgsValidator(),
                new TxtFileReader(),
                new TxtFileParser(),
                new TxtFileWriter()
        );
        dataService.process(args);
    }
}
