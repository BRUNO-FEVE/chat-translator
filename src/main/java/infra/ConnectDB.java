package infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException error) {
            throw new RuntimeException(error);
        }
    }

    public Connection connection() throws SQLException {
        String server = "localhost";
        String port = "3306";
        String database = "chat-translator-db";
        String username = "root";
        String password = "my-secret-pw";
        return DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, username, password);
    }
}

