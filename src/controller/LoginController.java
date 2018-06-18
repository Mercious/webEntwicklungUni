package controller;


import beans.UserSessionBean;
import services.UserService;
import utils.StringUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@WebServlet("/loginRegister")
public class LoginController extends AbstractBaseController {
    @Inject
    private UserService userService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        setHeaderData(request);
        response.setContentType(MediaType.TEXT_HTML);
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/WEB-INF/pages/loginRegister.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String eMail = request.getParameter("eMail");
        final String password = request.getParameter("password");
        if (StringUtils.isNotEmptyOrNull(eMail) && StringUtils.isNotEmptyOrNull(password)
                && this.userService.loginUser(eMail, password)) {
            UserSessionBean userSessionBean = userService.getUserSessionData(eMail);
            HttpSession userSession = request.getSession();
            userSession.setAttribute("currentUser", userSessionBean);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
        } else {
            addErrorMessages(request, "Fehler beim Login - E-Mail oder Passwort nicht korrekt.");
            request.getRequestDispatcher("/WEB-INF/pages/loginRegister.jsp").forward(request, response);
        }
    }
}
