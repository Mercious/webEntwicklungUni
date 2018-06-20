package controller;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends AbstractBaseController {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession currentSession = request.getSession(false);
        if (currentSession != null) {
            currentSession.invalidate();
        }
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
    }
}
