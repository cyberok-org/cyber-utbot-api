package org.testcases.cycle;


public class InfWhile2 {
    public boolean fun(int x, int y, int z) {
        boolean changeX = true;
        while (x == y) {
            if (changeX) {
                x += z;
            } else {
                y += z;
            }
            changeX = !changeX;
        }
        return false;
    }
}
