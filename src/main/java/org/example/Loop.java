package org.example;


public class Loop {
    private void fun2(int k) {
        int a = k;
    }

    public int whileLoop(int k) {
        if (k >= 0) {
            fun2(k);
        }
        int i = 0;
        int sum = 0;

        while (i < k) {
            sum += i;
            i += 1;
        }

        return sum;
    }
}
