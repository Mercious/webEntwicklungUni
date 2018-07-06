package services;

import beans.ArticleBean;
import beans.ConfiguratorBean;
import compatibility.CompatibilityStrategy;
import daos.ArticleDAO;


import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Autor: Felix Hartmann
@Stateless (name = "articleService")
public class ArticleService {
    final int maxTeaserNameLength = 40;

    @Inject
    private ArticleDAO articleDAO;

    @Inject
    private Instance<CompatibilityStrategy> compatibilityStrategies;

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


    public BufferedImage getImageForArticleWithDimensions(final String articleID, final int height, final int width) {
        BufferedInputStream imageData = this.getImageForArticle(articleID);
        if (imageData == null)
            return null;
        try {
            BufferedImage image = ImageIO.read(imageData);
            BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
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

    public List<String> getAllArticleIDs() {
        List<ArticleBean> allArticles = this.articleDAO.getAllArticles();
        return allArticles.stream().map(ArticleBean::getArticleID).collect(Collectors.toList());
    }

    public List<ArticleBean> setCompatibilityStatus(final List<ArticleBean> articleList, final ConfiguratorBean currentConfig) {
        if (currentConfig == null)
            return articleList;
        for (ArticleBean article : articleList) {
            for (CompatibilityStrategy compatibilityStrategy : this.compatibilityStrategies) {
                if (!compatibilityStrategy.isCompatibleToCurrentConfig(article, currentConfig)) {
                    article.setCompatibilityStatus(false);
                    break;
                }
            }
        }

        return articleList;
    }
}
