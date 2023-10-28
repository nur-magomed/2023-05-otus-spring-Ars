package edu.nur.homework03.io;

import edu.nur.homework03.exception.ReaderException;

import java.util.List;

public interface Reader {

    List<String[]> readAllLines() throws ReaderException;
}
