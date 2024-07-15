package com.client;

import com.actions.RoleMenuFunctionFactory;
import com.google.gson.Gson;
import com.model.UserSessionDTO;
import com.utility.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCafeteria {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    private static PrintWriter out;
    private static BufferedReader in;
    private static BufferedReader stdIn;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            login();

        } catch (IOException e) {
            System.err.println(Constants.ERROR_PREFIX + "Unable to connect to server. Check server and try again later.");
        }
    }

    private static void login() {
        try {
            System.out.println("Enter Employee ID:");
            String employeeId = stdIn.readLine();
            System.out.println("Enter Password:");
            String password = stdIn.readLine();
            out.println(Constants.LOGIN_REQUEST + employeeId + ";" + password);

            String response = in.readLine();
            if (response != null) {
                loginResponse(response, employeeId);
            } else {
                System.err.println(Constants.ERROR_PREFIX + "Server unreachable. Please try again.");
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_PREFIX + "Login error. Please try again.");
        }
    }

    private static void loginResponse(String response, String employeeId) {
        String[] parts = response.split(";");
        String responseType = parts[0];

        if (Constants.LOGIN_RESPONSE.equals(responseType)) {
            String status = parts[1];
            String message = parts[2];
            System.out.println(message);

            if (Constants.SUCCESS.equals(status)) {
                String role = parts[3];
                sendUserSessionRequest(employeeId, "login");
                executeRoleMenuCommand(employeeId,role);
            }
        } else {
            System.err.println(Constants.ERROR_PREFIX + "Unexpected response from server: " + response);
        }
    }

    public static void sendUserSessionRequest(String employeeId, String requestType) {
        UserSessionDTO sessionDTO = new UserSessionDTO();
        sessionDTO.setEmployeeId(employeeId);
        sessionDTO.setRequestType(requestType);
        Gson gson = new Gson();
        String jsonSession = gson.toJson(sessionDTO);
        out.println(Constants.USER_SESSION_REQUEST + jsonSession);
    }

    private static void executeRoleMenuCommand(String employeeId, String role) {
        try {
            RoleMenuFunctionFactory.getRoleMenuFunction(role).execute(stdIn,out,in,employeeId);
        } catch (IllegalArgumentException e) {
            System.err.println(Constants.ERROR_PREFIX + e.getMessage());
        } catch (IOException e) {
            System.err.println(Constants.ERROR_PREFIX + "Error displaying choice menu for role: " + role);
        }
    }
}
