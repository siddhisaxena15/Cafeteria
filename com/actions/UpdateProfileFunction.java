package com.actions;

import com.google.gson.Gson;
import com.model.ProfileDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.utility.Constants;

public class UpdateProfileFunction implements MenuFunction {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    private final String employeeId;

    public UpdateProfileFunction(PrintWriter out, BufferedReader in, BufferedReader stdIn, String employeeId) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
        this.employeeId = employeeId;
    }

    @Override
    public void execute() throws IOException {
        String diet = promptEnum("Enter your diet (Vegetarian, Non-Vegetarian, Eggetarian): ", new String[]{"Vegetarian", "Non-Vegetarian", "Eggetarian"});
        String spice = promptEnum("Enter your spice preference (High, Medium, Low): ", new String[]{"High", "Medium", "Low"});
        String cuisineType = promptEnum("Enter your Cuisine Choice (North Indian, South Indian, Other): ", new String[]{"North Indian", "South Indian", "Other"});
        String sweetTooth = promptEnum("Do you have a sweet tooth? (Yes, No): ", new String[]{"Yes", "No"});

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setEmployeeId(employeeId);
        profileDTO.setDiet(diet);
        profileDTO.setSpice(spice);
        profileDTO.setCuisineType(cuisineType);
        profileDTO.setSweetTooth(sweetTooth);

        Gson gson = new Gson();
        String json = gson.toJson(profileDTO);

        out.println(Constants.UPDATE_PROFILE_REQUEST + json);

        String response = in.readLine();
        handleProfileUpdateResponse(response);
    }

    private String promptEnum(String prompt, String[] validValues) throws IOException {
        String input;
        while (true) {
            System.out.print(prompt);
            input = stdIn.readLine();
            for (String value : validValues) {
                if (value.equalsIgnoreCase(input)) {
                    return value;
                }
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void handleProfileUpdateResponse(String response) {
        if (response != null && response.startsWith(Constants.UPDATE_PROFILE_RESPONSE)) {
            String[] responseParts = response.split(";");
            if (responseParts.length >= 2) {
                if (Constants.SUCCESS.equals(responseParts[1])) {
                    System.out.println("Profile updated successfully.");
                } else {
                    System.out.println("Failed to update profile: " + responseParts[2]);
                }
            } else {
                System.out.println("Invalid response format: " + response);
            }
        } else {
            System.out.println("Unexpected response from server: " + response);
        }
    }
}
