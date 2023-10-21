package edu.nur.io;

import edu.nur.exception.ReaderException;

import java.util.List;

public interface Reader {

    List<String[]> readAllLines() throws ReaderException;
}
