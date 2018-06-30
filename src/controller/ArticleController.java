package controller;


import beans.ArticleBean;
import daos.ArticleDAO;
import utils.StringUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/articles/articlesOfType")
public class ArticleController extends AbstractBaseController {

    @Inject
    private ArticleDAO articleDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String type = request.getParameter("type");
        if (!StringUtils.isNotEmptyOrNull(type))
            return;

        final boolean onlyCompatible = Boolean.valueOf(request.getParameter("onlyCompatible"));

        List<ArticleBean> allArticlesOfType = articleDAO.getAllArticlesOfType(type);
        if(onlyCompatible) {
            // do some work on this list to filter uncompatible elements
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(allArticlesOfType.toString());

    }

}
