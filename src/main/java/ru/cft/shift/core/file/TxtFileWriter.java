package ru.cft.shift.core.file;

import ru.cft.shift.config.OutputOptions;
import ru.cft.shift.core.data.WritableArraysDTO;

import java.io.File;
import java.math.BigInteger;
import java.io.IOException;
import java.io.FileWriter;

public class TxtFileWriter {
    private WritableArraysDTO outputArraysDTO;
    private String pathToFiles;
    private String prefixToFiles;
    private boolean appendStatus = false;

    public TxtFileWriter() {
        this.outputArraysDTO = new WritableArraysDTO();
    }

    public void setArgsCLI(OutputOptions outputOptions) {
        this.pathToFiles = outputOptions.getPath();
        this.prefixToFiles = outputOptions.getPrefix();
        this.appendStatus = outputOptions.isAppend();
    }

    public void writeToFiles(WritableArraysDTO outputArraysDTO) throws IOException {
        this.outputArraysDTO = outputArraysDTO;

        String currentDir = System.getProperty("user.dir");
        String outputPath;
        if (pathToFiles == null || pathToFiles.isEmpty()) {
            outputPath = currentDir;
        } else {
            outputPath = currentDir + pathToFiles;
        }

        File outputDir = new File(outputPath);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        if (prefixToFiles == null || prefixToFiles.isEmpty()) {
            prefixToFiles = "";
        }

        FileWriter integerWriter = new FileWriter(
                outputPath + File.separator + prefixToFiles + "integers.txt",
                appendStatus);
        for (BigInteger intNum : outputArraysDTO.getIntegersArray()) {
            if (intNum != null) {
                integerWriter.write(intNum.toString() + "\n");
            }
        }
        integerWriter.close();

        FileWriter doubleWriter = new FileWriter(
                outputPath + File.separator + prefixToFiles + "floats.txt",
                appendStatus);
        for (Double doubleNum : outputArraysDTO.getDoublesArray()) {
            if (doubleNum != null) {
                doubleWriter.write(doubleNum.toString() + "\n");
            }
        }
        doubleWriter.close();

        FileWriter stringWriter = new FileWriter(
                outputPath + File.separator + prefixToFiles + "strings.txt",
                appendStatus);
        for (String line : outputArraysDTO.getStringsArray()) {
            if (line != null) {
                stringWriter.write(line + "\n");
            }
        }
        stringWriter.close();
    }
}
