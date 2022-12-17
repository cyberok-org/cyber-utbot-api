package org.testcases.binaryOperators;


public class Or {
    public boolean fun(int x, int y, int z) {
        if (x == y || y == z || z == x) {
            return true;
        }
        x++;
        return false;
    }
}
