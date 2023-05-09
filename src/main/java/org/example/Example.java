package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Example {

    private static final String FIELD_NAME = "name";
    private PrintWriter writer;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter(FIELD_NAME);
        writer = resp.getWriter();
        foo(name);
    }

    private void foo(String s) {
        if (new Random().nextBoolean()) {
            write(s); // dangerous!
        } else {
            write(pseudoSanitize(s));
        }
    }

    private String pseudoSanitize(String s) {
        return "smth" + s.substring(2);
    }

    private void write(String data) {
        writer.write(data); // dangerous!
    }
}
