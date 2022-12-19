package org.testcases.execPaths;


public class ManyPathsProblem {
    public int fun(int x, int y) throws InterruptedException {
        int sum = 0;
        int res;
        int pr = 1;

        if (y > 0) {
            pr = -pr;
        }

        if (x % 2 == 0) {
            sum++;
        }
        if (x % 3 == 0) {
            sum++;
        }
        if (x % 5 == 0) {
            sum++;
        }
        if (x % 7 == 0) {
            sum++;
        }
        if (x % 11 == 0) {
            sum++;
        }
        if (x % 13 == 0) {
            sum++;
        }
        if (x % 17 == 0) {
            sum++;
        }
        switch (sum) {
            case 0:
                res = 1;
                break;
            case 1:
                res = 2;
                break;
            case 2:
                res = 3;
                break;
            case 3:
                res = 4;
                break;
            case 4:
                res = 5;
                break;
            case 5:
                res = 6;
                break;
            case 6:
                res = 7;
                break;
            case 7:
                res = 8;
                break;
            default:
                res = 9;
                break;
        }

//        if (y > 0) {
//            pr = -pr;
//        }

        return res * pr;
    }
}
