package org.example.checks;

import javax.servlet.http.HttpServletRequest;

public class Example {
    public void example(HttpServletRequest req) {
        req.getAuthType();
        req.getHeader("name");
    }
}
