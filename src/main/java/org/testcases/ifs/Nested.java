package org.testcases.ifs;


public class Nested {
    public boolean fun(int x, int y) {
        if (x % 3 == 2 && y % 5 == 4) {
            if (x % 7 == 6 && y % 11 == 10) {
                if (x == y) {
                    return true;
                }
            }
        }
        return false;
    }
}
