package com.service;

import com.DAO.UserSessionDAO;
import com.DTO.UserSession;

import java.sql.SQLException;

public class UserSessionService {
    private final UserSessionDAO userSessionDAO;

    public UserSessionService() {
        this.userSessionDAO = new UserSessionDAO();
    }

    public void logUserSession(UserSession session) throws SQLException {
        userSessionDAO.insertUserSession(session);
    }
}
