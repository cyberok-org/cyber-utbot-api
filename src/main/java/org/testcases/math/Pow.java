package org.testcases.math;

public class Pow {
    public boolean fun(int x) {
        if (x > Math.pow(2, 2) && x < Math.pow(2, 3)) {
            return true;
        }
        x++;
        return false;
    }
}
