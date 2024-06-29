package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class AdminMenuFunction implements RoleMenuFunction {
    @Override
    public void execute(BufferedReader stdIn,PrintWriter out, BufferedReader in, String employeeId) throws IOException {
        AdminController adminController = new AdminController(out,in,stdIn,employeeId);
        adminController.displayAdminMenu();
    }
}