package com.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.utility.Constants;


public class ViewNotificationsFunction implements MenuFunction {
    private final PrintWriter out;
    private final BufferedReader in;

    public ViewNotificationsFunction(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        out.println(Constants.VIEW_NOTIFICATIONS_REQUEST);

        String response = in.readLine();
        if (response != null && response.startsWith(Constants.VIEW_NOTIFICATIONS_RESPONSE)) {
            notificationResponseHandler(response);
        }
    }

    private void notificationResponseHandler(String response) {
        String[] parts = response.split(";");
        System.out.println("Notifications: ");
        for (int i = 1; i < parts.length; i++) {
            System.out.println(parts[i]);
        }
    }
}
