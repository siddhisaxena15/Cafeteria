package com.DAO;

import com.DTO.NotificationDTO;
import com.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public List<NotificationDTO> getTodayNotifications() throws SQLException {
        String query = "SELECT Message, CreatedDate FROM Notification WHERE DATE(CreatedDate) = CURDATE()";
        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setMessage(resultSet.getString("Message"));
                notificationDTO.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
                notificationDTOS.add(notificationDTO);
            }
        }
        return notificationDTOS;
    }
}
