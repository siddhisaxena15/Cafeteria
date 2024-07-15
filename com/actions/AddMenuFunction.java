package com.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
public class AddMenuFunction implements MenuFunction {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    public AddMenuFunction(BufferedReader stdIn, PrintWriter out, BufferedReader in) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        MenuHandler.addMenu(stdIn, out, in);
    }
}