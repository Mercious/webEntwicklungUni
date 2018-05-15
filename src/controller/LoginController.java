package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@WebServlet("/loginRegister")
public class LoginController extends AbstractBaseController {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        setHeaderData(request);
        response.setContentType(MediaType.TEXT_HTML);
        request.getRequestDispatcher("/WEB-INF/pages/loginRegister.jsp").forward(request, response);
    }
}
