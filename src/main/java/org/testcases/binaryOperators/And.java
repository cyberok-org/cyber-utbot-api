package org.testcases.binaryOperators;


public class And {
    public boolean fun(int x, int y, int z) {
        if (x == y && y == z) {
            return true;
        }
        x++;
        return false;
    }
}
