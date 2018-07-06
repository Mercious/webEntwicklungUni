package controller;

import services.ArticleService;
import utils.StringUtils;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;

// Autor: Felix Hartmann
@WebServlet("/image")
public class ImageController extends AbstractBaseController {
    @Inject
    private ArticleService articleService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        final String articleID = request.getParameter("articleID");
        if (!StringUtils.isNotEmptyOrNull(articleID))
            return;
        final boolean useTeaserScaling = Boolean.valueOf(request.getParameter("teaser"));
        response.setContentType("image/png");
        int height, width;
        if (useTeaserScaling) {
            height = width = 150;
        } else {
            height = width = 350;
        }

        BufferedImage imageDate = articleService.getImageForArticleWithDimensions(articleID, height, width);
        try {
            ImageIO.write(imageDate, "png", response.getOutputStream());
        } catch (IOException e) {
                // error handeling
        }
    }
}
