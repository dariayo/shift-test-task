package com.example.filefilter;

public class TypeStatistics {
    private final DataType type;
    private long count;
    private double sum;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private int shortestStringLength = Integer.MAX_VALUE;
    private int longestStringLength = Integer.MIN_VALUE;

    public TypeStatistics(DataType type) {
        this.type = type;
    }

    public void update(String value) {
        count++;
        if (type == DataType.INTEGER || type == DataType.FLOAT) {
            double number = Double.parseDouble(value);
            sum += number;
            min = Math.min(min, number);
            max = Math.max(max, number);
        } else if (type == DataType.STRING) {
            int length = value.length();
            shortestStringLength = Math.min(shortestStringLength, length);
            longestStringLength = Math.max(longestStringLength, length);
        }
    }

    public String getSummary(boolean full) {
        StringBuilder summary = new StringBuilder("Count: " + count);
        if (full) {
            if (type == DataType.INTEGER || type == DataType.FLOAT) {
                double average = count > 0 ? sum / count : 0;
                summary.append(", Min: ").append(min)
                        .append(", Max: ").append(max)
                        .append(", Sum: ").append(sum)
                        .append(", Average: ").append(average);
            } else if (type == DataType.STRING) {
                summary.append(", Shortest String Length: ")
                        .append(shortestStringLength == Integer.MAX_VALUE ? 0 : shortestStringLength)
                        .append(", Longest String Length: ")
                        .append(longestStringLength == Integer.MIN_VALUE ? 0 : longestStringLength);
            }
        }
        return summary.toString();
    }
}
