package org.example;

public class Loop2 {
    public int whileLoop(int k) {
        int i = 0;
        int sum = 0;

        while (i < k) {
            sum += i;
            i += 1;
        }

        return sum;
    }
}
