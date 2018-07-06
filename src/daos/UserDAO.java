package daos;


import beans.User;
import utils.StringUtils;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;


// Autor: Serkan Altay
@Stateless
public class UserDAO extends AbstractBaseDAO {
    @Resource(lookup = "jdbc/MyTHIPool")
    private DataSource dataSource;

    public String getPasswordForUser(final String userID) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer WHERE Email=?");
            statement.setString(1, userID);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE Email = ?");
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setCity(resultSet.getString("Ort"));
                user.setFirstName(resultSet.getString("Vorname"));
                user.setLastName(resultSet.getString("Nachname"));
                user.seteMail(resultSet.getString("Email"));
                user.setUserRole(resultSet.getString("Rolle"));
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

    public boolean registerUser(final String eMail, final String password, final String firstName, final String lastName) {
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer (Email, Vorname, Nachname, Passwort)" +
                    "VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, eMail);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, StringUtils.hashPassword(password));
            int result = preparedStatement.executeUpdate();
            return result != 0;

        } catch (SQLException e) {
            return false;
        } finally {
            closeConnection(connection);
        }
    }
}
