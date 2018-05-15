package daos;

import java.sql.Connection;
import java.sql.SQLException;

public class AbstractBaseDAO {

    protected void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            // Log warning
        }
    }
}
