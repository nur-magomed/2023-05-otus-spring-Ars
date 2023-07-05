package edu.nur.io;

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
