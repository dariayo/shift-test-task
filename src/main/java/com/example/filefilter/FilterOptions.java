package com.example.filefilter;

import java.util.*;

public class FilterOptions {
    private final List<String> inputFiles;
    private final String outputDir;
    private final String prefix;
    private final boolean appendMode;
    private final boolean fullStatistics;

    public FilterOptions(List<String> inputFiles, String outputDir, String prefix, boolean appendMode, boolean fullStatistics) {
        this.inputFiles = inputFiles;
        this.outputDir = outputDir;
        this.prefix = prefix;
        this.appendMode = appendMode;
        this.fullStatistics = fullStatistics;
    }

    public static FilterOptions parseArgs(String[] args) {
        List<String> inputFiles = new ArrayList<>();
        String outputDir = ".";
        String prefix = "";
        boolean appendMode = false;
        boolean fullStatistics = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    outputDir = args[++i];
                    break;
                case "-p":
                    prefix = args[++i];
                    break;
                case "-a":
                    appendMode = true;
                    break;
                case "-f":
                    fullStatistics = true;
                    break;
                default:
                    inputFiles.add(args[i]);
                    break;
            }
        }

        if (inputFiles.isEmpty()) {
            throw new IllegalArgumentException("No input files specified.");
        }

        return new FilterOptions(inputFiles, outputDir, prefix, appendMode, fullStatistics);
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public String getOutputFileName(DataType type) {
        return outputDir + "/" + prefix + type.getFileSuffix() + ".txt";
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public boolean isFullStatistics() {
        return fullStatistics;
    }
}
