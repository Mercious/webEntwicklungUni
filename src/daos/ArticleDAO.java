package daos;

import beans.ArticleBean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            if(results.next()) {
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

}
