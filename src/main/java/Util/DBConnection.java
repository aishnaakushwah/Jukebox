package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("ALL")
public class DBConnection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/jukebox";
        String user = "root";
        String pass = "root@123";
        connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }
}
