package DAO;

import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongsDAO {

    public String returnPath(int songId) throws SQLException, ClassNotFoundException {
        String path = "";
        Connection connection = DBConnection.getConnection();
        String query = "Select filepath from songs where songId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, songId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            path = resultSet.getString(1);
        }
        return path;
    }
}

