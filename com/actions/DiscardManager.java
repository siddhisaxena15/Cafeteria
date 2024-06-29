package com.actions;

import com.utility.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class DiscardManager {
    private final PrintWriter out;
    private final BufferedReader in;
    private final BufferedReader stdIn;

    public DiscardManager(PrintWriter out, BufferedReader in, BufferedReader stdIn) {
        this.out = out;
        this.in = in;
        this.stdIn = stdIn;
    }

    public void userActionsHandler(String role) throws IOException {
        if ("chef".equalsIgnoreCase(role) || "admin".equalsIgnoreCase(role)) {
            chefAdminActionsHandler();
        } else if ("employee".equalsIgnoreCase(role)) {
            employeeActionsHandler();
        } else {
            System.out.println("Invalid role.");
        }
    }

    private void chefAdminActionsHandler() throws IOException {
        out.println(Constants.DISCARD_MENU_REQUEST);
        String response = in.readLine();

        if (response != null && response.startsWith(Constants.DISCARD_MENU_RESPONSE)) {
            Map<String, List<String>> menuMap = parseMenuResponse(response);
            displayMenuItemsComments(menuMap);

            String selectedMenuItem = getUserInput("Enter Menu Id to proceed:");
            out.println(selectedMenuItem);

            String actionResponse = in.readLine();
            actionResponseHandler(actionResponse, selectedMenuItem);
        } else {
            System.out.println("Failed to retrieve low-rated menu items.");
        }
    }

    private void employeeActionsHandler() throws IOException {
        out.println(Constants.DISCARD_FEEDBACK_DETAILS_REQUEST_FOR_EMPLOYEE);
        String response = in.readLine();
        response = response.replaceAll("[{}]", "");
        Map<Integer, String> menuIdWithQuestionMap = parseFeedbackDetailsResponse(response);
        displayFeedbackQuestions(menuIdWithQuestionMap);

        String choice = stdIn.readLine();
        employeeResponseHandler(menuIdWithQuestionMap, choice);
    }

    private Map<Integer, String> parseFeedbackDetailsResponse(String response) {
        return Arrays.stream(response.split(","))
                .map(entry -> entry.split("="))
                .collect(Collectors.toMap(
                        entry -> Integer.parseInt(entry[0].trim()),
                        entry -> entry[1].trim()
                ));
    }

    private void displayFeedbackQuestions(Map<Integer, String> menuIdWithQuestionMap) {
        System.out.println("Choose one of the following Menu Ids to provide Feedback: " + menuIdWithQuestionMap.keySet());
    }

    private void employeeResponseHandler(Map<Integer, String> menuIdWithQuestionMap, String choice) throws IOException {
        int menuId = Integer.parseInt(choice);
        if (menuIdWithQuestionMap.containsKey(menuId)) {
            String[] questions = menuIdWithQuestionMap.get(menuId).split(";");
            StringBuilder employeeResponse = new StringBuilder();
            for (String question : questions) {
                System.out.println(question);
                employeeResponse.append(stdIn.readLine()).append(";");
            }
            System.out.println("Thanks for your Feedback!");
            System.out.println("Here are your responses:");
            System.out.println(employeeResponse);
        } else {
            System.out.println("Incorrect Menu Id");
        }
    }

    private Map<String, List<String>> parseMenuResponse(String response) {
        String[] parts = response.split(";");
        Map<String, List<String>> menuMap = new HashMap<>();

        for (int i = 1; i < parts.length; i++) {
            String menuItem = parts[i];
            i++;
            List<String> comments = new ArrayList<>();
            while (i < parts.length && !parts[i].contains("[")) {
                comments.add(parts[i]);
                i++;
            }
            i--;
            menuMap.put(menuItem, comments);
        }
        return menuMap;
    }

    private void displayMenuItemsComments(Map<String, List<String>> menuMap) {
        for (Map.Entry<String, List<String>> entry : menuMap.entrySet()) {
            System.out.println("Food Item (MenuId, Average Rating, Dish Name): " + entry.getKey());
            System.out.println("Comments: ");
            for (String comment : entry.getValue()) {
                System.out.println(comment);
            }
        }
    }

    private String getUserInput(String prompt) throws IOException {
        System.out.println(prompt);
        return stdIn.readLine();
    }

    private void actionResponseHandler(String actionResponse, String selectedMenuItem) throws IOException {
        if (actionResponse != null && actionResponse.startsWith(Constants.DISCARD_MENU_ACTION_VALID)) {
            String action = getUserInput("Choose an action: 1) Remove 2) Get Detailed Feedback");
            out.println(action);

            if (Constants.ACTION_REMOVE.equals(action)) {
                removeActionHandler();
            } else if (Constants.ACTION_DETAILS.equals(action)) {
                out.println(Constants.DISCARD_FEEDBACK_DETAILS_REQUEST_FOR_ADMIN_CHEF + selectedMenuItem);
                System.out.println("Request sent for detailed feedback.");
            }
        } else if (actionResponse != null) {
            System.out.println(actionResponse.split(";")[2]);
        }
    }

    private void removeActionHandler() throws IOException {
        String result = in.readLine();
        System.out.println(result.split(";")[2]);
    }
}
