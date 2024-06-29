package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ChefMenuFunction implements RoleMenuFunction {
    @Override
    public void execute(BufferedReader stdIn,PrintWriter out, BufferedReader in, String employeeId) throws IOException {
        ChefController chefController = new ChefController(out,in,stdIn,employeeId);
        chefController.displayChefMenu();
    }
}