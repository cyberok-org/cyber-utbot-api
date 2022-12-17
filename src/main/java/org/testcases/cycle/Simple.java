package org.testcases.cycle;


public class Simple {
    public boolean fun(int x) {
        for (int i = 0; i < 100; i++) {
            if (i == x) {
                return true;
            }
        }
        return false;
    }
}
