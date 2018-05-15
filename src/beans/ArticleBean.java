package beans;

import java.io.Serializable;

public class ArticleBean implements Serializable {
    public ArticleBean(final String articleName, final String articleID, final double price) {
        this.articleName = articleName;
        this.articleID = articleID;
        this.price = price;
    }
    private String articleName;
    private String articleID;
    private double price;

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
