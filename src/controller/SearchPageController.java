package controller;

import beans.ArticleBean;
import beans.ConfiguratorBean;
import daos.ArticleDAO;
import services.ArticleService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


// Autor: Serkan Altay
@WebServlet("/search")
public class SearchPageController extends AbstractBaseController {

    @Inject
    private ArticleDAO articleDAO;

    @Inject
    private ArticleService articleService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String searchTerm = request.getParameter("searchTerm");
        List<ArticleBean> matchingArticles = this.articleDAO.searchForArticle(searchTerm);
        matchingArticles = this.articleService.setCompatibilityStatus(matchingArticles, request.getSession(false)
                != null ? (ConfiguratorBean) request.getSession().getAttribute("currentConfig") : null);
        request.setAttribute("articles", matchingArticles);
        request.getRequestDispatcher("/WEB-INF/pages/searchPage.jsp").forward(request, response);
    }

}
