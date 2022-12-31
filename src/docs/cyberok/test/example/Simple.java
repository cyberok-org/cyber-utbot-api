package org.testcases;

public class Simple {
    public int fun(int x, int y) {
        int a = x;
        if (x > 0 && x * 2 < 0) {
            a = y;
        }
        return a + x;
    }
}
