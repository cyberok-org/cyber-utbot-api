package org.testcases.math;

public class OnePow {
    public boolean fun(int x) {
        if (x > Math.pow(2, 2)) {
            return true;
        }
        x++;
        return false;
    }
}
