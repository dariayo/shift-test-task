package com.example.filefilter;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileProcessor {
    private final FilterOptions options;
    private final Map<DataType, List<String>> dataMap = new HashMap<>();
    private final Statistics statistics = new Statistics();

    public FileProcessor(FilterOptions options) {
        this.options = options;
        for (DataType type : DataType.values()) {
            dataMap.put(type, new ArrayList<>());
        }
    }

    public void processFiles() throws IOException {
        for (String inputFile : options.getInputFiles()) {
            Path path = Paths.get(inputFile);
            if (!Files.exists(path)) {
                System.err.println("Warning: File " + inputFile + " does not exist. Skipping.");
                continue;
            }
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    classifyAndStore(line);
                }
            }
        }
        writeOutputFiles();
    }

    private void classifyAndStore(String line) {
        if (line.matches("-?\\d+")) {
            dataMap.get(DataType.INTEGER).add(line);
            statistics.update(DataType.INTEGER, line);
        } else if (line.matches("-?\\d*\\.\\d+([eE]-?\\d+)?")) {
            dataMap.get(DataType.FLOAT).add(line);
            statistics.update(DataType.FLOAT, line);
        } else {
            dataMap.get(DataType.STRING).add(line);
            statistics.update(DataType.STRING, line);
        }
    }

    private void writeOutputFiles() throws IOException {
        for (DataType type : DataType.values()) {
            List<String> data = dataMap.get(type);
            if (!data.isEmpty()) {
                String fileName = options.getOutputFileName(type);
                Path outputPath = Paths.get(fileName);
                OpenOption[] openOptions = options.isAppendMode()
                        ? new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.APPEND }
                        : new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING };
                try (BufferedWriter writer = Files.newBufferedWriter(outputPath, openOptions)) {
                    for (String item : data) {
                        writer.write(item);
                        writer.newLine();
                    }
                }
            }
        }
    }

    public void printStatistics() {
        statistics.print(options.isFullStatistics());
    }
}
