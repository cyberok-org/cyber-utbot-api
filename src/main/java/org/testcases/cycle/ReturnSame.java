package org.testcases.cycle;


public class ReturnSame {
    public int fun(int x) {
        for (int i = 0; i < x; i++) {
            if (i > 99) {
                return x;
            }
        }
        return x;
    }
}
