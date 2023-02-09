package org.testcases.temp;

public class Temp {
    public int foo(int x, int y) {
        int a = x;
        int b = 2;
        if (x < y) {
            vulnerable(a);
            throw new ArithmeticException("...");
        } else {
            b = y;
        }
        return a * b;
    }

    public void vulnerable(int a) {
        a += 12;
    }

    public int bar() {
        return -10;
    }

    public void fooCaller() {
        foo(bar(), 12);
    }
}
