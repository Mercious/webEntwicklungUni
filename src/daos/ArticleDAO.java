package daos;

import beans.ArticleBean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// Autor: Felix Hartmann
@Stateless(name = "articleDAO")
public class ArticleDAO extends AbstractBaseDAO {

    @Resource(lookup = "jdbc/MyTHIPool")
    private DataSource dataSource;

    final String ALL_BUT_PICTURE_SELECT = "SELECT Artikelnummer, Beschreibung, Preis, CPU_Slot, GPU_Slot, RAM_Slot, " +
            "Kategorie FROM artikel";


    public List<ArticleBean> getAllArticlesOfType(final String type) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement searchStatement = connection.createStatement();
            String query = ALL_BUT_PICTURE_SELECT;
            // type is not user input, no escaping needed
            if (type != null) {
                query = query + " WHERE Kategorie = '" + type + "'";
            }
            searchStatement.execute(query);
            ResultSet results = searchStatement.getResultSet();
            List<ArticleBean> resultList = new ArrayList<>();
            while (results.next()) {
                resultList.add(new ArticleBean(results.getString("Beschreibung"),
                        results.getString("Artikelnummer"), results.getDouble("Preis"), results.getString("Kategorie"),
                        results.getString("CPU_Slot"), results.getString("GPU_Slot"), results.getString("RAM_Slot")));
            }

            return resultList;

        } catch (SQLException e) {
            // Error handeling
            return Collections.emptyList();
        } finally {
            closeConnection(connection);
        }
    }

    public ArticleBean getArticle(final String articleID) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement searchStatement = connection.prepareStatement(ALL_BUT_PICTURE_SELECT + " WHERE Artikelnummer =?");
            searchStatement.setString(1, articleID);
            ResultSet results = searchStatement.executeQuery();
            if (results.next()) {
                return new ArticleBean(results.getString("Beschreibung"),
                        results.getString("Artikelnummer"), results.getDouble("Preis"), results.getString("Kategorie"),
                        results.getString("CPU_Slot"), results.getString("GPU_Slot"), results.getString("RAM_Slot"));
            }

            return null;


        } catch (SQLException e) {
            // error handeling
            return null;
        }
    }

    public List<ArticleBean> getAllArticles() {
        return this.getAllArticlesOfType(null);
    }


    public BufferedInputStream getImageForArticle(final String articleID) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement searchStatement = connection.prepareStatement("SELECT Bild FROM artikel WHERE Artikelnummer =?");
            searchStatement.setString(1, articleID);
            ResultSet results = searchStatement.executeQuery();
            if (results.next()) {
                return new BufferedInputStream(results.getBinaryStream("Bild"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            // error handling
            return null;
        } finally {
            closeConnection(connection);
        }

    }

    public List<ArticleBean> searchForArticle(final String searchTerm) {
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ALL_BUT_PICTURE_SELECT + " WHERE Beschreibung LIKE ?");
            preparedStatement.setString(1, "%" + searchTerm + "%");
            ResultSet results = preparedStatement.executeQuery();
            List<ArticleBean> resultList = new ArrayList<>();
            while (results.next()) {
                resultList.add(new ArticleBean(results.getString("Beschreibung"),
                        results.getString("Artikelnummer"), results.getDouble("Preis"), results.getString("Kategorie"),
                        results.getString("CPU_Slot"), results.getString("GPU_Slot"), results.getString("RAM_Slot")));
            }

            return resultList;

        } catch (SQLException e) {
            return null;
        } finally {
            closeConnection(connection);
        }
    }


    public boolean updateArticle(final String articleID, final String articleName, final String GPUSlot, final String CPUSlot,
                                 final String RAMSlot, final double price, final InputStream newFileStream) {
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE artikel SET Beschreibung = ?," +
                    " CPU_Slot = ?, GPU_Slot = ?, RAM_Slot = ?, Preis = ?" +
                    (newFileStream != null ? ", Bild = ?" : "") + "  WHERE Artikelnummer = ?;");
            preparedStatement.setString(1, articleName);
            preparedStatement.setString(2, CPUSlot);
            preparedStatement.setString(3, GPUSlot);
            preparedStatement.setString(4, RAMSlot);
            preparedStatement.setDouble(5, price);
            if (newFileStream != null) {
                preparedStatement.setBinaryStream(6, newFileStream);
                preparedStatement.setString(7, articleID);
            } else {
                preparedStatement.setString(6, articleID);
            }

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows != 0;

        } catch (SQLException e) {
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    public boolean createNewArticle(final String articleID, final String articleName, final String category, final String GPUSlot, final String CPUSlot,
                                    final String RAMSlot, final double price, final InputStream newFileStream) {


        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO artikel (Artikelnummer," +
                    " Beschreibung, Kategorie, CPU_Slot, GPU_Slot, RAM_Slot, Preis, Bild) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, articleID);
            preparedStatement.setString(2, articleName);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, CPUSlot);
            preparedStatement.setString(5, GPUSlot);
            preparedStatement.setString(6, RAMSlot);
            preparedStatement.setDouble(7, price);
            preparedStatement.setBinaryStream(8, newFileStream);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows != 0;

        } catch (SQLException e) {
            return false;
        } finally {
            closeConnection(connection);
        }
    }

}
