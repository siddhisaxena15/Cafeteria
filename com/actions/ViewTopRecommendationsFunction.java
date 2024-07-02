package com.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.MenuDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import com.utility.Constants;


public class ViewTopRecommendationsFunction implements MenuFunction {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    public ViewTopRecommendationsFunction(BufferedReader stdIn, PrintWriter out, BufferedReader in) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        String numberOfRecommendation = promptString("Enter number of recommendations you want: ");
        out.println(Constants.VIEW_TOP_RECOMMENDATIONS + numberOfRecommendation);
        String recommendationsResponse = in.readLine();
        printTopRecommendations(recommendationsResponse);
    }

    private void printTopRecommendations(String recommendationsResponse){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<MenuDTO>>() {}.getType();
        List<MenuDTO> recommendations = gson.fromJson(recommendationsResponse, listType);

        StringBuilder formattedResponse = new StringBuilder("Top Recommendations:\n\n");
        for (MenuDTO recommendation : recommendations) {
            formattedResponse.append("Name: ").append(recommendation.getName()).append("\n")
                    .append("Menu ID: ").append(recommendation.getMenuId()).append("\n")
                    .append("Price: Rs. ").append(recommendation.getPrice()).append("\n")
                    .append("Availability: ").append(recommendation.getAvailabilityStatus()).append("\n")
                    .append("Meal Type: ").append(recommendation.getMealType()).append("\n")
                    .append("Score: ").append(recommendation.getScore()).append("\n\n");
        }

        System.out.println(formattedResponse);
    }

    private String promptString(String prompt) throws IOException {
        System.out.print(prompt);
        return stdIn.readLine();
    }
}