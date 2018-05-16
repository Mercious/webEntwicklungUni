package daos;


import beans.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;

@Stateless
public class UserDAO extends AbstractBaseDAO {
    @Resource(lookup = "jdbc/MyTHIPool")
    private DataSource dataSource;

    public String getPasswordForUser(final String userID) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE Email=?");
            statement.setString(1, userID);
            ResultSet results = statement.executeQuery();
            if(results.next()) {
                return results.getString("Passwort");
            } else {
                return "";
            }
        } catch (SQLException e) {
            // error handeling
            return "";
        } finally {
            closeConnection(connection);
        }
    }

    public User getUser(final String userID) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHRE Email=?");
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User user = new User();
                user.setCity(resultSet.getString("Stadt"));
                // ...
                return user;
            } else {
                return null;
            }

        } catch (SQLException e) {
            // error handeling
            return null;
        } finally {
            closeConnection(connection);
        }
    }
}
