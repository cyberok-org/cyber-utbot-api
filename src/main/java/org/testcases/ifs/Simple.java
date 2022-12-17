package org.testcases.ifs;


public class Simple {
    public int oneIf(int x, int y) {
        int a = 1;
        int b = 2;
        if (x == 5) {
            a = y;
        } else {
            b = y;
        }
        return a * b;
    }
}
