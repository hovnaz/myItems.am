import core.db.DBConnectionProvider;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
        System.out.println(connection);
    }
}
