package org.example.dir;

public class Loop3 {
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
