package org.testcases.ifs;


public class ZeroDivision {
    public boolean fun(int x, int y) {
        if (y > 0 && (x / (2 * (y + 1)) == 0)) {
            return true;
        }
        y++;
        return false;
    }
}
