package org.testcases.ifs;

public class TernaryNested {
    public int fun(int x, int y, int z) {
        return x == 2 ? (y == 3 ? 1 : 2) : (z == 4 ? 3 : 5);
    }
}
