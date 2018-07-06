package controller;


import beans.ArticleBean;
import beans.ConfiguratorBean;
import compatibility.CompatibilityStrategy;
import daos.ArticleDAO;
import utils.StringUtils;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Autor: Felix Hartmann
@WebServlet("/articles/articlesOfType")
public class ArticleController extends AbstractBaseController {

    @Inject
    private ArticleDAO articleDAO;

    @Inject
    private Instance<CompatibilityStrategy> compatibilityStrategies;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String type = request.getParameter("type");
        if (!StringUtils.isNotEmptyOrNull(type))
            return;

        final boolean onlyCompatible = Boolean.valueOf(request.getParameter("onlyCompatible"));

        List<ArticleBean> allArticlesOfType = articleDAO.getAllArticlesOfType(type);
        if(onlyCompatible) {
            HttpSession currentSession = request.getSession(false);
            if (currentSession != null && currentSession.getAttribute("currentConfig") != null) {
                allArticlesOfType = allArticlesOfType.stream().filter(article -> {
                    for (CompatibilityStrategy strategy : compatibilityStrategies) {
                        if (!strategy.isCompatibleToCurrentConfig(article, (ConfiguratorBean) currentSession.getAttribute("currentConfig")))
                            return false;
                    }
                    return true;
                }).collect(Collectors.toList());
            }
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        allArticlesOfType.sort(Comparator.comparingDouble(ArticleBean::getPrice));
        response.getWriter().write(this.articleListToJSON(allArticlesOfType));

    }


    private String articleListToJSON(final List<ArticleBean> articleList) {
        StringBuilder jsonResult = new StringBuilder("[");
        for (int i = 0; i < articleList.size(); i++) {
            ArticleBean bean = articleList.get(i);
            boolean notLast = (i != articleList.size() - 1);
            jsonResult.append("{");
            jsonResult.append("\"articleID\":\"").append(bean.getArticleID()).append("\",");
            jsonResult.append("\"articleName\":\"").append(bean.getArticleName().replaceAll("\t", "")).append("\",");
            jsonResult.append("\"price\":").append(bean.getPrice()).append("}"); if (notLast) jsonResult.append(",");
        }
        jsonResult.append("]");
        return jsonResult.toString();
    }

}
