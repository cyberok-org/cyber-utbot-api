package org.testcases.ifs;

public class FermatsLastTheorem {
    public boolean fun(int x, int y, int z) {
        if (x > 0 && y > 0 && z > 0
            && x < 1291 && y < 1291 && z < 1291      // Math.pow(Integer.MAX_VALUE, 1.0/3)
            && x * x * x + y * y * y == z * z * z) {
            return true;
        }
        x++;
        return false;
    }
}
