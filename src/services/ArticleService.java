package services;

import beans.ArticleBean;
import daos.ArticleDAO;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless (name = "articleService")
public class ArticleService {
    final int maxTeaserNameLength = 40;

    @Inject
    private ArticleDAO articleDAO;

    public List<ArticleBean> getArticleForHomepageTeaser() {
        List<ArticleBean> allArticles = articleDAO.getAllArticles();
        allArticles.forEach(article -> {
            if (article.getArticleName().length() > maxTeaserNameLength) {
                article.setArticleName(article.getArticleName().substring(0, maxTeaserNameLength - 3) + "...");
            }
        });
        Collections.shuffle(allArticles);
        return allArticles.subList(0, Math.min(6, allArticles.size()));
    }

    public BufferedInputStream getImageForArticle(final String articleID) {
        return articleDAO.getImageForArticle(articleID);
    }

    public BufferedImage getTeaserImageForArticle(final String articleID) {
        BufferedInputStream imageData = this.getImageForArticle(articleID);
        try {
            BufferedImage image = ImageIO.read(imageData);
            BufferedImage scaledImage = new BufferedImage(150, 150, image.getType());
            Graphics2D graphics = scaledImage.createGraphics();
            graphics.drawImage(image, 0, 0, scaledImage.getWidth(), scaledImage.getHeight(), 0, 0, image.getWidth(),
                    image.getHeight(), null);
            graphics.dispose();
            return scaledImage;
        } catch (IOException e) {
            // error handling
        }
        return null;
    }
}
