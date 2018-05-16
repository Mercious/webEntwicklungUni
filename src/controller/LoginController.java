package controller;


import services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;

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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        final String eMail = request.getParameter("eMail");
        final String password = request.getParameter("password");
        boolean result = this.userService.loginUser(eMail, password);
        if(result) {
            request.getRequestDispatcher("/WEB-INF/pages/homePage.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessages", Collections.singleton("Fehler beim Login - E-Mail oder Passwort nicht korrekt"));
            request.getRequestDispatcher("/WEB-INF/pages/loginRegister.jsp").forward(request, response);
        }
    }


}
