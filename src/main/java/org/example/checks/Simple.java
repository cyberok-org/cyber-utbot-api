package org.example.checks;

public class Simple {
    private void internal(String s, int x) {
    }

    public boolean example(String s, int x) {
        if (s.length() > 3 && x > 100) {
            internal(s, x);
            return true;
        }
        return false;
    }
}
