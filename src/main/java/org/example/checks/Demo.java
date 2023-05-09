package org.example.checks;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo {
    public void example(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = "safe_path.txt";
        String header = request.getHeader("header");
        if (header.equals("some value")) {
            Cookie[] theCookies = request.getCookies();
            if (request.getHeader("header2").equals("some value2")) {
                if (theCookies[5].getValue().equals("cookie value")) {
                    if (theCookies[5].getName().equals("cookie name")) {
                        path = request.getParameter(theCookies[9].getPath());
                    }
                }
            }
        }

        java.io.File fileTarget = new java.io.File(path);

//        System.out.println(path);
        response.getWriter().println("file: " + fileTarget);
    }
}
