package com.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.model.MenuDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewMenuFunction implements MenuFunction {
    private final PrintWriter out;
    private final BufferedReader in;

    public ViewMenuFunction(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        out.println("VIEW_MENU_REQUEST");
        String viewResponse = in.readLine();
        printMenu(viewResponse);
    }

    void printMenu(String viewResponse){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonPart = viewResponse.split(";")[1];
        jsonPart = jsonPart.trim();
        if (jsonPart.startsWith("{") && jsonPart.endsWith("}")) {
            jsonPart = jsonPart.substring(1, jsonPart.length() - 1).trim();
        }
        List<MenuDTO> menuDTOList = gson.fromJson(jsonPart, new TypeToken<List<MenuDTO>>() {}.getType());
        String formattedJson = gson.toJson(menuDTOList);
        System.out.println(formattedJson);
    }
}