package libraries.copy;

import java.sql.*;

public class AuthenticationService {
    private static Connection connection;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "geekbrains";
    private static final String URL = "jdbc:mysql://localhost:3306/ichat";

    private void connectToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL is not found.");
            e.printStackTrace();
            return;
        }
        System.out.println("MySQL successfully connected");
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
            return;
        }
        System.out.println("Connected to MySQL");
    }

    AuthenticationService() {
        connectToDB();
    }

    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean changeNickname(String username, String nickname) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET nickname = '" + nickname + "' WHERE username = '" + username + "';");
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public String getNickByLoginAndPwd(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT nickname FROM users WHERE username = '" + username + "' AND password = '" + password + "';");
            ResultSet result = statement.executeQuery();
            result.next();
            String nickname = result.getString(1);
            if (result != null) {
                return nickname;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
