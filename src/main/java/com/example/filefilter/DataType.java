package com.example.filefilter;

public enum DataType {
    INTEGER("integers"),
    FLOAT("floats"),
    STRING("strings");

    private final String fileSuffix;

    DataType(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }
}
