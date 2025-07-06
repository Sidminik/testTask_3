package ru.cft.shift.core.file;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class TxtFileReader {
    private final List<String[]> fileContents;

    public TxtFileReader() {
        fileContents = new ArrayList<>();
    }

    public void readFile(List<String> fileNamesCLI) throws IOException {
        for (String fileName : fileNamesCLI) {
            String[] lines = Files.readAllLines(Paths.get(fileName)).toArray(new String[0]);
            fileContents.add(lines);
        }
    }

    public List<String[]> getFileContents() {
        if (fileContents.isEmpty()) {
            return null;
        } else {
            return fileContents;
        }
    }
}
