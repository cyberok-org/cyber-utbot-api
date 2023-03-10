package org.example;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletExample extends HttpServlet {
    public boolean temp(HttpServletResponse response) throws IOException {
        if (response != null) {
            response.setContentType("text/html;charset=UTF-8");
            return true;
        }
        return false;
    }
}
