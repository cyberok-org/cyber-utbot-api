package org.example.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

    public class Inter1 {
        private static final String FIELD_NAME = "name";
        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String s1 = req.getParameter(FIELD_NAME);
            String s2 = id(s1);
            String s3 = id("abc");
            PrintWriter writer = resp.getWriter();
            writer.println(s2);                    /* BAD */
            writer.println(s3);                    /* OK */
        }
        private String id(String string) {
            return string;
        }
        public String getDescription() {
            return "simple id method call";
        }
        public int getVulnerabilityCount() {
            return 1;
        }
    }

