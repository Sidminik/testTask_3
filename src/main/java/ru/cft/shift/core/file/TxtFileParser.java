package ru.cft.shift.core.file;

import ru.cft.shift.core.data.WritableArraysDTO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TxtFileParser {
    private List<String[]> inputFileContents;
    private final WritableArraysDTO outputArraysDTO;

    public TxtFileParser() {
        this.inputFileContents = new ArrayList<>();
        this.outputArraysDTO = new WritableArraysDTO();
    }

    public void parseFile(List<String[]> fileContents) {
        this.inputFileContents = fileContents;

        int maxLength = 0;
        for (String[] array : inputFileContents) {
            if (array.length > maxLength) {
                maxLength = array.length;
            }
        }
        for (int i = 0; i < maxLength; i++) {
            for (String[] array : inputFileContents) {
                if (i < array.length) {
                    try {
                        outputArraysDTO.addInteger(new BigInteger(array[i]));
                    } catch (NumberFormatException e1) {
                        try {
                            outputArraysDTO.addDouble(Double.parseDouble(array[i]));
                        } catch (NumberFormatException e2) {
                            outputArraysDTO.addString(array[i]);
                        }
                    }
                }
            }
        }
    }

    public WritableArraysDTO getOutputArraysDTO() {
        return outputArraysDTO;
    }
}
