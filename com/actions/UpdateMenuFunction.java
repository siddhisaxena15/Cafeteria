package com.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
public class UpdateMenuFunction implements MenuFunction {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    public UpdateMenuFunction(BufferedReader stdIn, PrintWriter out, BufferedReader in) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        MenuHandler.updateMenu(stdIn, out, in);
    }
}