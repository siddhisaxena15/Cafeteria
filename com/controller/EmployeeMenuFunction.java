package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class EmployeeMenuFunction implements RoleMenuFunction {
    @Override
    public void execute(BufferedReader stdIn,PrintWriter out, BufferedReader in, String employeeId) throws IOException {
        EmployeeController employeeController= new EmployeeController(out, in, stdIn,employeeId);
        employeeController.displayEmployeeMenu();
    }
}