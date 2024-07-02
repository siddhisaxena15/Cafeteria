package com.actions;

import com.google.gson.Gson;
import com.model.FeedbackDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.utility.Constants;

public class FeedbackToChefFunction implements MenuFunction {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    private final String employeeId;

    public FeedbackToChefFunction(BufferedReader stdIn, PrintWriter out, BufferedReader in, String employeeId) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
        this.employeeId = employeeId;
    }

    @Override
    public void execute() throws IOException {
        int menuId = Integer.parseInt(promptString("Enter the MenuId to provide feedback: "));
        String comment = promptString("Enter comment: ");
        int rating = Integer.parseInt(promptString("Enter rating: "));

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setEmployeeId(employeeId);
        feedbackDTO.setMenuId(menuId);
        feedbackDTO.setComment(comment);
        feedbackDTO.setRating(rating);

        Gson gson = new Gson();
        String jsonFeedback = gson.toJson(feedbackDTO);
        out.println(Constants.GIVE_FEEDBACK_REQUEST + jsonFeedback);

        String feedbackResponse = in.readLine();
        if (feedbackResponse != null && feedbackResponse.startsWith(Constants.GIVE_FEEDBACK_RESPONSE)) {
            handleFeedbackResponse(feedbackResponse);
        }
    }

    private void handleFeedbackResponse(String feedbackResponse) {
        String[] feedbackParts = feedbackResponse.split(";");
        if (Constants.SUCCESS.equals(feedbackParts[1])) {
            System.out.println("Feedback submitted!");
        } else {
            System.out.println("Failed to submit feedback: " + feedbackParts[2]);
        }
    }

    private String promptString(String prompt) throws IOException {
        System.out.print(prompt);
        return stdIn.readLine();
    }
}
