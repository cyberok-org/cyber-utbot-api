package org.testcases.math;

public class PowOverlap {
    public boolean fun(int x) {
        if (x > Math.pow(2, 3) && x < Math.pow(2, 2)) {
            return true;
        }
        x++;
        return false;
    }
}
