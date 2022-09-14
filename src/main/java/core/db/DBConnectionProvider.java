package core.db;

public class DatabaseConnectionProvider {
    private DatabaseConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
