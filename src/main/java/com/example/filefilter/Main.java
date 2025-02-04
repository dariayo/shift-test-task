package com.example.filefilter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FilterOptions options = FilterOptions.parseArgs(args);
            FileProcessor processor = new FileProcessor(options);
            processor.processFiles();
            processor.printStatistics();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}
