package controller;

import services.ArticleService;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;

@WebServlet("/image")
public class ImageController extends AbstractBaseController {
    @Inject
    private ArticleService articleService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        final String articleID = request.getParameter("articleID");
        final boolean useTeaserScaling = Boolean.valueOf(request.getParameter("teaser"));
        response.setContentType("image/png");

        if (useTeaserScaling) {
            BufferedImage imageDate = articleService.getTeaserImageForArticle(articleID);
            try {
                ImageIO.write(imageDate, "png", response.getOutputStream());
            } catch (IOException e) {
                // error handeling
            }
        } else {
            BufferedInputStream imageData = articleService.getImageForArticle(articleID);
            byte buffer[] = new byte[1024];
            int read = 0;
            try  {
                while( (read = imageData.read(buffer, 0, buffer.length)) != -1 ) {
                    response.getOutputStream().write(buffer, 0, read);
                }
            } catch (IOException e) {
                // error handling
            }

        }
    }
}
