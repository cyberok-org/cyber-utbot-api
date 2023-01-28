package org.testcases.temp;

import java.util.Random;

public class Temp {
    public static int foo(int x, int y) {
        int a = x;
        int b = 2;
        if (x <= y) {
            vulnerable(a);
        } else {
            b = y;
        }
        return a * b;
    }

    private static void vulnerable(int a) {
        System.out.println("danger!" + a);
    }

    public static int bar() {
        return -10;
    }

    public static void main(String[] args) {
        Random r = new Random();
        foo(bar(), r.nextInt());
    }
}
