package org.testcases.ifs;

public class TernaryOneVar {
    public int fun(int x) {
        int a = x == 2 ? 1 : 2;
        int b = x >= 2 ? 3 : 5;
        return a * b;
    }
}
