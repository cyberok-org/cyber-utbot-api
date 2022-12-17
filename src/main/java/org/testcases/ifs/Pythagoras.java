package org.testcases.ifs;

public class Pythagoras {
    public boolean fun(int x, int y, int z) {
        if (x > 0 && y > 0 && z > 0
            && x < 46341 && y < 46341 && z < 46341     // Math.pow(Integer.MAX_VALUE, 0.5)
            && x * x + y * y == z * z) {
            return true;
        }
        x++;
        return false;
    }
}
