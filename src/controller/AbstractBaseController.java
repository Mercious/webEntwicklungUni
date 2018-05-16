package controller;

import beans.HeaderBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;


public abstract class AbstractBaseController extends HttpServlet {
    public void writeViewContentToResponse(String viewName, HttpServletResponse response) throws IOException {
        Path filePath = Paths.get(new File(getServletContext().getRealPath("WEB-INF/" + viewName)).toURI());
    }

    protected void setHeaderData(final HttpServletRequest request) {
        HeaderBean headerBean = new HeaderBean();
        headerBean.setUserLoggedIn(true);
        request.setAttribute("headerBean", headerBean);
    }

    protected void addErrorMessages(final HttpServletRequest request, String... messages) {

    }

    protected void addErrorMessages(final HttpServletRequest request, Collection<String> messages) {

    }
}
