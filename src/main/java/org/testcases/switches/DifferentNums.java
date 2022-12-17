package org.testcases.switches;


public class DifferentNums {
    public int fun(int x) {
        int result = -1;
        switch (x) {
            case Integer.MAX_VALUE:
                result = 1;
                break;
            case 255:
                result = 2;
                break;
            case -999999:
                result = 3;
                break;
            case Integer.MIN_VALUE:
                result = 4;
                break;
        }
        return result;
    }
}
