package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


@WebServlet("/helloworld")
public class HelloWorldController extends AbstractBaseController {
    private static String VIEW_NAME = "HelloWorld.html";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.TEXT_HTML);
        writeViewContentToResponse(VIEW_NAME, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.TEXT_HTML);
        response.getWriter().write(request.getParameter("userName") + " : " + request.getParameter("password"));
    }
}
