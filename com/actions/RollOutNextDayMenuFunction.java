package com.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.utility.Constants;


public class RollOutNextDayMenuFunction implements MenuFunction {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    public RollOutNextDayMenuFunction(BufferedReader stdIn, PrintWriter out, BufferedReader in) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        System.out.print("Enter the MenuIds for the next day (comma separated): ");
        String menuIds = stdIn.readLine();
        out.println(Constants.ROLLOUT_NEXT_DAY_MENU_REQUEST + menuIds);

        String response = in.readLine();
        if (response != null && response.startsWith(Constants.ROLLOUT_NEXT_DAY_MENU_RESPONSE)) {
            String[] parts = response.split(";");
            if ("SUCCESS".equals(parts[1])) {
                System.out.println("Next day's menu rolled out successfully.");
            } else {
                System.out.println("Failed to roll out next day's menu.");
            }
        }
    }
}