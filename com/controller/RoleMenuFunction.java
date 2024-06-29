package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface RoleMenuFunction {
    void execute(BufferedReader stdIn, PrintWriter out, BufferedReader in, String employeeId) throws IOException;
}
