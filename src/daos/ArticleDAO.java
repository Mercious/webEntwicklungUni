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

@Stateless(name = "articleDAO")
public class ArticleDAO extends AbstractBaseDAO {

    @Resource(lookup = "jdbc/MyTHIPool")
    private DataSource dataSource;

    public List<ArticleBean> getAllArticles() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement searchStatement = connection.createStatement();
            searchStatement.execute("select Artikelnummer, Beschreibung, Preis from artikel");
            ResultSet results = searchStatement.getResultSet();
            List<ArticleBean> resultList = new ArrayList<>();
            while (results.next()) {
                resultList.add(new ArticleBean(results.getString("Beschreibung"), results.getString("Artikelnummer"),
                        results.getDouble("Preis")));
            }
            return resultList;
        } catch (SQLException e) {
            // Error handeling
            return Collections.emptyList();
        } finally {
            closeConnection(connection);
        }
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

}
