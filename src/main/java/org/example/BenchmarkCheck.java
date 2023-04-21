package org.example;

import org.utbot.engine.overrides.collections.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

public class BenchmarkCheck {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getParameter("param");

        for(int i = 0; i < 100; i++) {
            PrintWriter writer = resp.getWriter();
            if(i > 5) {
                writer.println(s);                    /* BAD */
            }
        }
    }

}
