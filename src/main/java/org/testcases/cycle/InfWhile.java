package org.testcases.cycle;


public class InfWhile {
    public boolean fun(int x, int y) {
        while (x == y) {
            x++;
            y++;
        }
        return false;
    }
}
