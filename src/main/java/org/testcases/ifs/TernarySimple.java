package org.testcases.ifs;

public class TernarySimple {
    public int fun(int x, int y) {
        int a = x == y ? 1 : 2;
        int b = x >= y ? 3 : 5;
        return a * b;
    }
}
