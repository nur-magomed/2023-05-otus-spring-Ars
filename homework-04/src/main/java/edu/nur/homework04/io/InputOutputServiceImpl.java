package edu.nur.homework04.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputOutputServiceImpl implements InputOutputService {

    private final PrintStream output;

    private final Scanner input;

    public InputOutputServiceImpl(PrintStream output, InputStream inputStream) {
        this.output = output;
        this.input  = new Scanner(inputStream);;
    }

    @Override
    public String inputString() {
        return input.nextLine();
    }

    @Override
    public int inputInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }

}
