package edu.nur.io;

import java.io.InputStream;
import java.util.Scanner;

public class InputServiceStream implements InputService {

    private final Scanner input;

    public InputServiceStream(InputStream input) {
        this.input = new Scanner(input);
    }

    @Override
    public String inputString() {
        return input.nextLine();
    }

    @Override
    public int inputInt() {
        return 0;
    }

}
