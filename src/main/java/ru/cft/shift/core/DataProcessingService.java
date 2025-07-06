package ru.cft.shift.core;

import ru.cft.shift.cli.ArgsParser;
import ru.cft.shift.cli.ArgsValidator;
import ru.cft.shift.cli.MapperArgsToConfig;
import ru.cft.shift.config.Config;
import ru.cft.shift.core.file.TxtFileReader;
import ru.cft.shift.core.file.TxtFileParser;
import ru.cft.shift.core.file.TxtFileWriter;
import ru.cft.shift.core.statistics.services.StatisticProcessingService;

import java.io.IOException;

public class DataProcessingService {
    private final ArgsParser parserCLI;
    private final ArgsValidator validatorCLI;
    private final TxtFileReader txtFileReader;
    private final TxtFileParser txtFileParser;
    private final TxtFileWriter txtFileWriter;

    public DataProcessingService(ArgsParser parserCLI,
                                 ArgsValidator validatorCLI,
                                 TxtFileReader txtFileReader,
                                 TxtFileParser txtFileParser,
                                 TxtFileWriter txtFileWriter) {
        this.parserCLI = parserCLI;
        this.validatorCLI = validatorCLI;
        this.txtFileReader = txtFileReader;
        this.txtFileParser = txtFileParser;
        this.txtFileWriter = txtFileWriter;
    }

    public void process(String[] args) throws IOException {
        validatorCLI.validateInputArgs(args);
        parserCLI.parse(args);
        validatorCLI.validate(parserCLI);

        Config config = MapperArgsToConfig.map(parserCLI);

        txtFileReader.readFile(config.getInputFiles());

        txtFileParser.parseFile(txtFileReader.getFileContents());

        txtFileWriter.setArgsCLI(config.getOutputOptions());
        txtFileWriter.writeToFiles(txtFileParser.getOutputArraysDTO());

        if (parserCLI.hasCommandStatistics()) {
            StatisticProcessingService statisticProcessingService = new StatisticProcessingService(
                        config.getStatisticsMode(), txtFileParser.getOutputArraysDTO());
            statisticProcessingService.printStatistics();
        }
    }
}
