package com.example.filefilter;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private final Map<DataType, TypeStatistics> stats = new HashMap<>();

    public void update(DataType type, String value) {
        stats.putIfAbsent(type, new TypeStatistics(type));
        stats.get(type).update(value);
    }

    public void print(boolean full) {
        stats.forEach((type, stat) -> {
            System.out.println(type.name() + " statistics:");
            System.out.println(stat.getSummary(full));
        });
    }
}
