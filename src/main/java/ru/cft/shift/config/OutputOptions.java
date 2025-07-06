package ru.cft.shift.config;

public class OutputOptions {
    private final String path;
    private final String prefix;
    private final boolean append;

    public OutputOptions(String path, String prefix, boolean append) {
        this.path = path;
        this.prefix = prefix;
        this.append = append;
    }

    public String getPath() {
        return path;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isAppend() {
        return append;
    }
}
