package org.testcases.ifs;

public class PrimeNumber {
    public boolean fun(int x, int y) {
        if (x * y == 97 && x > 0 && x < 97 && y > 0 && y < 97) {        // 97 - prime
            return true;
        }
        x++;
        return false;
    }
}
