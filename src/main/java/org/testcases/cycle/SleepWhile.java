package org.testcases.cycle;


public class SleepWhile {
    public boolean fun(int x) throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            if (x == 5) {
                return true;
            }
            break;
        }
        return false;
    }
}
