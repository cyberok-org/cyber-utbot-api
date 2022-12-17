package org.testcases.cycle;


public class ForLimit {
    public boolean fun(int x) {
        int limit = 45;
        for (int i = 0; i < limit; i++) {
            if (i == x && i == limit-1) {
                return true;
            }
        }
        return false;
    }
}
