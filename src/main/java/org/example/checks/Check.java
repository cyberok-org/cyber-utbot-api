package org.example.checks;

import java.io.IOException;

public class Check {
    public void example(String s, int i) throws IOException {
        if (s.equals("abc")) return;
        if (s.length() > 10) return;
        if (i > 5 || i < -5) return;

        new java.io.File(s);
    }
}
