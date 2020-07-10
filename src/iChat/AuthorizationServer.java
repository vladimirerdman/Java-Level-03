package iChat;

import java.io.IOException;
import java.sql.*;

public class AuthorizationServer {
    private static Connection connectionToDb;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "geekbrains";
    private static final String URL = "jdbc:mysql://localhost:3306/ichat?" + "autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
    MainLogger log = new MainLogger();

    private void connectToDB() throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //System.out.println("MySQL JDBC Driver is not found.");
            log.setLogMessage("MySQL JDBC Driver is not found.");
            e.printStackTrace();
            return;
        }
        System.out.println("MySQL JDBC Driver successfully connected");
        try {
            connectionToDb = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            //System.out.println("Connection failed.");
            log.setLogMessage("Connection failed.");
            e.printStackTrace();
            return;
        }
        System.out.println("Connected to MySQL");
    }

    AuthorizationServer() throws IOException {
        connectToDB();
    }

    public void closeConnection() throws IOException {
        try {
            if (!connectionToDb.isClosed()) {
                connectionToDb.close();
            }
        } catch (SQLException throwables) {
            log.setLogMessage("Close connection error");
            throwables.printStackTrace();
        }
    }

    public boolean changeNickname(String username, String nickname) throws IOException {
        try {
            PreparedStatement statement = connectionToDb.prepareStatement("UPDATE users SET nickname = '" + nickname + "' WHERE username = '" + username + "';");
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException throwables) {
            log.setLogMessage("Nickname change error");
            throwables.printStackTrace();
        }
        return false;
    }

    public String getNickByLoginAndPwd(String username, String password) throws IOException {
        try {
            PreparedStatement statement = connectionToDb.prepareStatement("SELECT nickname FROM users WHERE username = '" + username + "' AND password = '" + password + "';");
            ResultSet result = statement.executeQuery();
            result.next();
            String nickname = result.getString(1);
            if (result != null) {
                return nickname;
            }
        } catch (SQLException throwables) {
            log.setLogMessage("Authorization error");
            throwables.printStackTrace();
        }
        return null;
    }

}