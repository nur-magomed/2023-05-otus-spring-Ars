package edu.nur.homework03.io;

import org.springframework.stereotype.Service;

import java.io.PrintStream;


public class OutputServiceStream implements OutputService {

    private final PrintStream output;

    public OutputServiceStream(PrintStream output) {
        this.output = output;
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }

}
