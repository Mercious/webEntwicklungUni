package controller;

import javax.annotation.Resource;
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
import daos.ArticleDAO;
import services.ArticleService;


@WebServlet("")
public class HomePageController extends AbstractBaseController {
    private final String VIEW_NAME = "HomePage.jsp";

    @Inject
    private ArticleService articleService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        setHeaderData(request);
        response.setContentType(MediaType.TEXT_HTML);
        List<ArticleBean> articleTeaserList = articleService.getArticleForHomepageTeaser();
        request.setAttribute("articleTeaserList", articleTeaserList);
        request.getRequestDispatcher("/WEB-INF/pages/homePage.jsp").forward(request, response);
    }
}
