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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/configurator")
public class ConfiguratorController extends AbstractBaseController {

    @Inject
    private ArticleService articleService;

    @Inject
    private ArticleDAO articleDAO;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/pages/myConfigurator.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String action = request.getParameter("action");
        if("removeAll".equals(action) || "remove".equals(action)) {
            HttpSession currentSession = request.getSession(false);
            // if there is no session yet, that means there is no one logged in and nothing has has been added to the configurator yet
            // So nothing has to be removed, just return
            if (currentSession == null)
                return;

            if ("removeAll".equals(action)) {
                // set a new, empty configurator bean, which replaces the old one
                currentSession.setAttribute("currentConfig", new ConfiguratorBean());
            } else {
                final String articleToRemove = request.getParameter("articleID");
                ConfiguratorBean currentConfig = (ConfiguratorBean) currentSession.getAttribute("currentConfig");
                currentConfig.getItems().remove(articleToRemove);
            }
            request.getRequestDispatcher("/WEB-INF/pages/myConfigurator.jsp").forward(request, response);
            return;
        }

        if("add".equals(action)) {
            HttpSession currentSession = request.getSession();
            final String articleID = request.getParameter("articleID");
            ArticleBean article = articleDAO.getArticle(articleID);
            ConfiguratorBean currentConfig = (ConfiguratorBean) currentSession.getAttribute("currentConfig");
            currentConfig.getItems().put(article.getType(), article);
        }


    }



}
