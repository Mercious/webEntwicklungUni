package controller;

import beans.ArticleBean;
import daos.ArticleDAO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchPageController extends AbstractBaseController {

    @Inject
    private ArticleDAO articleDAO;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String searchTerm = request.getParameter("searchTerm");
        List<ArticleBean> matchingArticles = this.articleDAO.searchForArticle(searchTerm);
        request.setAttribute("articles", matchingArticles);
        request.getRequestDispatcher("/WEB-INF/pages/searchPage.jsp").forward(request, response);
    }

}
