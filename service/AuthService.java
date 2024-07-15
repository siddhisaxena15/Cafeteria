package com.service;

import com.DAO.UserDAO;
import com.DTO.UserDTO;

public class AuthService {

    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public UserDTO authenticate(String employeeId, String password) {
        return userDAO.getUserByEmployeeIdAndPassword(employeeId, password);
    }
}
