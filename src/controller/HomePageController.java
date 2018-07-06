package controller;

import javax.annotation.Resource;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

import beans.ArticleBean;
import beans.ConfiguratorBean;
import compatibility.CompatibilityStrategy;
import daos.ArticleDAO;
import services.ArticleService;


// Autor: Serkan Altay
@WebServlet("")
public class HomePageController extends AbstractBaseController {
    private final String VIEW_NAME = "HomePage.jsp";

    @Inject
    private ArticleService articleService;

    @Inject
    private Instance<CompatibilityStrategy> compatibilityStrategies;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        setHeaderData(request);
        response.setContentType(MediaType.TEXT_HTML);
        List<ArticleBean> articleTeaserList = articleService.getArticleForHomepageTeaser();
        if (request.getSession(false) != null && request.getSession().getAttribute("currentConfig") != null) {
            articleTeaserList.forEach(article -> {
                boolean compatible = true;
                for (CompatibilityStrategy compatibilityStrategy : compatibilityStrategies) {
                    compatible = compatibilityStrategy.isCompatibleToCurrentConfig(article, (ConfiguratorBean) request.getSession().getAttribute("currentConfig"));
                    if (!compatible)
                        break;
                }
                article.setCompatibilityStatus(compatible);
            });
        }
        request.setAttribute("articleTeaserList", articleTeaserList);
        request.getRequestDispatcher("/WEB-INF/pages/homePage.jsp").forward(request, response);
    }
}
