package com.actions;

import com.google.gson.Gson;
import com.model.MenuDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import com.utility.Constants;

public class MenuHandler {

    public static void addMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        try{
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setName(promptString(stdIn, "Enter Name: "));
            menuDTO.setPrice(promptBigDecimal(stdIn, "Enter Price: "));
            menuDTO.setAvailabilityStatus("Yes");
            menuDTO.setMealType(promptString(stdIn, "Enter Meal Type (Breakfast/Lunch/Dinner): "));
            menuDTO.setScore(new BigDecimal(0));
            menuDTO.setDiet(promptString(stdIn, "Enter Diet (Vegetarian/Non-Vegetarian/Eggetarian): "));
            menuDTO.setSpice(promptString(stdIn, "Enter Spice level (High/Medium/Low): "));
            menuDTO.setCuisineType(promptString(stdIn, "Enter Cuisine Type (North Indian/South Indian/Other): "));
            menuDTO.setSweetTooth(promptString(stdIn, "Enter Sweet Tooth (Yes/No): "));

            Gson gson = new Gson();
            String json = gson.toJson(menuDTO);
            out.println(Constants.ADD_MENU_REQUEST + json);
            System.out.println(in.readLine());
        }catch(IllegalArgumentException | InputMismatchException error){
            System.err.println("Input is invalid. Please Try again");
        }catch(Exception error){
            System.err.println(error.getMessage());
        }
    }

    public static void updateMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        try {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenuId(promptBigDecimal(stdIn, "Enter Menu Id: "));
            menuDTO.setName(promptString(stdIn, "Enter Name: "));
            menuDTO.setPrice(promptBigDecimal(stdIn, "Enter Price: "));
            menuDTO.setAvailabilityStatus(promptString(stdIn, "Enter Availability Status (Yes/No): "));
            menuDTO.setMealType(promptString(stdIn, "Enter Meal Type: "));
            menuDTO.setDiet(promptString(stdIn, "Enter Diet (Vegetarian/Non-Vegetarian/Eggetarian): "));
            menuDTO.setSpice(promptString(stdIn, "Enter Spice level (High/Medium/Low): "));
            menuDTO.setCuisineType(promptString(stdIn, "Enter Preference (North Indian/South Indian/Other): "));
            menuDTO.setSweetTooth(promptString(stdIn, "Enter Sweet Tooth (Yes/No): "));

            Gson gson = new Gson();
            String json = gson.toJson(menuDTO);
            out.println(Constants.UPDATE_MENU_REQUEST + json);
            System.out.println(in.readLine());
        }catch(IllegalArgumentException | InputMismatchException error){
            System.err.println("Input is invalid. Please Try again");
        }catch(Exception error){
            System.err.println(error.getMessage());
        }
    }


    public static void deleteMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        try {
            String menuId = promptString(stdIn, "Enter Menu ID: ");
            out.println(Constants.DELETE_MENU_REQUEST + menuId);
            System.out.println(in.readLine());
        }catch(IllegalArgumentException | InputMismatchException error){
            System.err.println("Input is invalid. Please Try again");
        }catch(Exception error){
            System.err.println(error.getMessage());
        }
    }

    private static String promptString(BufferedReader stdIn, String prompt) throws IOException {
        System.out.print(prompt);
        return stdIn.readLine();
    }

    private static BigDecimal promptBigDecimal(BufferedReader stdIn, String prompt) throws IOException {
        System.out.print(prompt);
        return new BigDecimal(stdIn.readLine());
    }
}
