package edu.nur.homework04.io;

import edu.nur.homework04.exception.ReaderException;

import java.util.List;

public interface Reader {

    List<String[]> readAllLines() throws ReaderException;
}
