package controller;



import beans.UserSessionBean;
import daos.UserDAO;
import services.UserService;
import utils.StringUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends AbstractBaseController {

    @Inject
    private UserDAO userDAO;
    @Inject
    private UserService userService;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String eMail = request.getParameter("eMail");
        final String password = request.getParameter("password");
        final String passwordRepeat = request.getParameter("passwordRepeat");
        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");

        if(StringUtils.areNotEmptyOrNull(eMail, password, firstName, lastName) && password.equals(passwordRepeat)) {
            boolean result = this.userService.registerUser(eMail, password, firstName, lastName);
            if(result) {
                UserSessionBean userSessionBean = new UserSessionBean(firstName, lastName, eMail);
                HttpSession userSession = request.getSession();
                userSession.setAttribute("currentUser", userSessionBean);
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
            } else {
                addErrorMessages(request, "Fehler bei der Registrierung");
                request.getRequestDispatcher("/WEB-INF/pages/loginRegister.jsp").forward(request, response);
            }
        } else {
            addErrorMessages(request, "Fehlerhafte Eingaben, bitte korrigieren!");
            request.getRequestDispatcher("/WEB-INF/pages/loginRegister.jsp").forward(request, response);
        }
    }
}
