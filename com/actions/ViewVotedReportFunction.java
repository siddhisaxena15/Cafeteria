package com.actions;

import com.utility.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
public class ViewVotedReportFunction implements MenuFunction {
    private final PrintWriter out;
    private final BufferedReader in;

    public ViewVotedReportFunction(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        out.println(Constants.VIEW_VOTED_REPORT);
        String votedReportResponse = in.readLine();
        printVotedReport(votedReportResponse);
    }

    private void printVotedReport(String votedReportResponse) {
        if (votedReportResponse.startsWith("VOTED_REPORT")) {
            String[] parts = votedReportResponse.split(";");
            StringBuilder formattedReport = new StringBuilder("Chef Menu:\n\n");

            for (int i = 1; i < parts.length; i++) {
                String[] details = parts[i].split(", ");
                String menuId = details[1].split(": ")[1];
                String voteCount = details[2].split(": ")[1];

                formattedReport.append("Menu ID: ").append(menuId).append("\n");
                formattedReport.append("Vote Count: ").append(voteCount).append("\n\n");
            }

            System.out.println(formattedReport.toString());
        } else {
            System.out.println("Unexpected response: " + votedReportResponse);
        }
    }
}