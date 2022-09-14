package core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private final static DBConnectionProvider INSTANCE = new DBConnectionProvider();
    private Connection connection;

    private static final String DB_URL = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6519582";
    private static final String DB_USERNAME = "sql6519582";
    private static final String DB_PASSWORD = "PNHwRsSljD";

    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionProvider getINSTANCE() {
        var x = INSTANCE;
        System.out.println(x);
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
