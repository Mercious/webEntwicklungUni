package controller;



import beans.ArticleBean;
import beans.UserSessionBean;
import daos.ArticleDAO;
import enums.CategoryEnum;
import services.ArticleService;
import utils.StringUtils;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


// Autor: Felix Hartmann und Serkan Altay zu gleichen teilen
@WebServlet("/adminArea")
@MultipartConfig
class AdminAreaController  extends AbstractBaseController {

    @Inject
    private ArticleDAO articleDAO;

    @Inject
    private ArticleService articleService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // check privileges
        final UserSessionBean currentUser = request.getSession(false) != null ? (UserSessionBean) request.getSession().getAttribute("currentUser") : null;
        if (currentUser == null || !"Admin".equals(currentUser.getUserRole())) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
            return;
        }

        final String articleID = request.getParameter("articleID");
        if (StringUtils.isNotEmptyOrNull(articleID)) {
            ArticleBean article = this.articleDAO.getArticle(articleID);
            request.setAttribute("currentSelection", article);
        }

        request.setAttribute("articleIDList", this.articleService.getAllArticleIDs());
        addCategoryValuesToRequest(request);
        request.getRequestDispatcher("/WEB-INF/pages/adminArea.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // check privileges
        final UserSessionBean currentUser = request.getSession(false) != null ? (UserSessionBean) request.getSession().getAttribute("currentUser") : null;
        if (currentUser == null || !"Admin".equals(currentUser.getUserRole())) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
            return;
        }

        final String articleID = request.getParameter("articleID");
        final ArticleBean articleBean = this.articleDAO.getArticle(articleID);
        request.setAttribute("articleIDList", this.articleService.getAllArticleIDs());

        addCategoryValuesToRequest(request);

        final String articleName = request.getParameter("articleName");
        final String CPUSlot = request.getParameter("CPUSlot");
        final String GPUSlot = request.getParameter("GPUSlot");
        final String RAMSlot = request.getParameter("RAMSlot");
        double price;
        try {
            price = Double.valueOf(request.getParameter("price"));
        } catch (NumberFormatException e) {
            addErrorMessages(request, "Fehler - Ungültiger Preis.");
            request.getRequestDispatcher("/WEB-INF/pages/adminArea.jsp").forward(request, response);
            return;
        }

        final Part newPicturePart = request.getPart("newPicture");
        InputStream newPictureStream = null;
        if (newPicturePart != null && newPicturePart.getSize() != 0)
            newPictureStream = newPicturePart.getInputStream();
        if (newPicturePart.getSize() >= 104857600) {
            addErrorMessages(request, "Fehler - Maximale Upload-Größe von 100MB wurde überschritten - bitte kleineres Bild wählen.");
            request.getRequestDispatcher("/WEB-INF/pages/adminArea.jsp").forward(request, response);
        }

        if(articleBean == null) {
            // articleID that was submitted is not yet known, so user is trying to add new articl
            final String category = request.getParameter("category");
            if (CategoryEnum.getEnumFor(category) == null) {
                addErrorMessages(request, "Fehler - Ungültige Kategorie.");
            }

            else if(this.articleDAO.createNewArticle(articleID, articleName, category, GPUSlot, CPUSlot, RAMSlot, price, newPictureStream)) {
                addSuccessMessage(request, "Erfolgreich eingefügt");
            } else {
                addErrorMessages(request, "Fehler - Artikel konnte nicht erzeugt werden.");
            }
            // reset the ID list, since we just added a new one
            request.setAttribute("articleIDList", this.articleService.getAllArticleIDs());
            request.getRequestDispatcher("/WEB-INF/pages/adminArea.jsp").forward(request, response);
            return;
        }

        if (this.articleDAO.updateArticle(articleID, articleName, GPUSlot, CPUSlot, RAMSlot, price, newPictureStream)) {
            addSuccessMessage(request, "Erfolgreich gespeichert.");
        } else {
            addErrorMessages(request, "Fehler - Das Update war nicht erfolgreich");
        }

        request.getRequestDispatcher("/WEB-INF/pages/adminArea.jsp").forward(request, response);


    }


    private void addCategoryValuesToRequest(HttpServletRequest request) {
        List<String> categoryNames = new ArrayList<>();
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            categoryNames.add(categoryEnum.toString());
        }

        request.setAttribute("categoryValues", categoryNames);
    }
}
