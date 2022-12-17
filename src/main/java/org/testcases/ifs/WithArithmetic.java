package org.testcases.ifs;


public class WithArithmetic {
    public boolean fun(int x, int y) {
        if ((x * y + 1) / 2 == 32) {
            return true;
        }
        if (x % y == 1_000_000 - 1) {
            return true;
        }
        return false;
    }
}
