package org.testcases.cycle;


public class Nested {
    public boolean fun(int x) {
        if (x > 0) {
            return false;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    x++;
                    if (x == 125) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
