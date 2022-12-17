package org.testcases.switches;


public class Simple {
    public int fun(int x) {
        int result = -1;
        switch (x) {
            case 3:
                result = 1;
//                break;
            case 1:
                result = 2;
                break;
            case 2:
                result = 0;
                break;
            default:
                result = x;
        }
        return result;
    }
}
