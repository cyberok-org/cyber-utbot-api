package org.testcases.ifs;


public class OneIfThreePath {
    public boolean fun(int x, int y) {
        if (y > 0 && (x / (y + 1) == 0)) {
            return true;
        }
        y++;
        return false;
    }
}
