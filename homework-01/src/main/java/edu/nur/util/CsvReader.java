package edu.nur.util;

public class CsvReader {

    private String fileName;

    public CsvReader(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
