package com.DAO;

import com.DTO.UserDTO;
import com.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDTO getUserByEmployeeIdAndPassword(String employeeId, String password) {
        String query = "SELECT user.*, role.RoleName FROM user " +
                "JOIN role ON user.RoleID = role.RoleID " +
                "WHERE user.EmployeeID = ? AND user.Password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, employeeId);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapToUser(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return null;
    }

    private UserDTO mapToUser(ResultSet resultSet) throws SQLException {
        UserDTO userDTO = new UserDTO();
        //user.setUserId(resultSet.getInt("UserID"));
        userDTO.setEmployeeId(resultSet.getString("EmployeeID"));
        //user.setName(resultSet.getString("Name"));
        userDTO.setPassword(resultSet.getString("Password"));
        userDTO.setRoleId(resultSet.getInt("RoleID"));
        userDTO.setRoleName(resultSet.getString("RoleName"));
        return userDTO;
    }
}
