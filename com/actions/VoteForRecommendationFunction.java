package com.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.utility.Constants;

public class VoteForRecommendationFunction implements MenuFunction {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;
    private final String employeeId;

    public VoteForRecommendationFunction(BufferedReader stdIn, PrintWriter out, BufferedReader in, String employeeId) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
        this.employeeId = employeeId;
    }

    @Override
    public void execute() throws IOException {
        out.println(Constants.VIEW_CHEF_RECOMMENDATIONS);
        String response = in.readLine();

        if (response != null && response.startsWith(Constants.VIEW_RECOMMENDATIONS_RESPONSE)) {
            chefRecommendationsHandler(response);
        }
    }

    private void chefRecommendationsHandler(String response) throws IOException {
        String[] parts = response.split(";");
        System.out.println("Recommendations by the Chef:");
        for (int i = 1; i < parts.length; i++) {
            System.out.println(parts[i]);
        }
        String menuIds = promptString("Enter the MenuIds to vote for (comma separated): ");
        out.println(Constants.VOTE_RECOMMENDATION_REQUEST + menuIds + ";" + employeeId);

        String voteResponse = in.readLine();
        if (voteResponse != null && voteResponse.startsWith(Constants.VOTE_RECOMMENDATION_RESPONSE)) {
            handleVoteResponse(voteResponse);
        }
    }

    private void handleVoteResponse(String voteResponse) {
        String[] voteParts = voteResponse.split(";");
        if (Constants.SUCCESS.equals(voteParts[1])) {
            System.out.println("Votes registered successfully.");
        } else {
            System.out.println("Failed to submit feedback: " + voteParts[2]);
        }
    }

    private String promptString(String prompt) throws IOException {
        System.out.print(prompt);
        return stdIn.readLine();
    }
}
