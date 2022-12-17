package org.testcases.ifs;

public class TernaryManyVarsSimple {
    public int fun(int x, int y, int z) {
        int a = x == y ? 1 : 2;
        int b = x == z ? 3 : 5;
        int c = z == y ? 7 : 11;

        int result = a * b * c;
        switch (a * b * c) {
            case 21:
                result -= 1;
                break;
            case 55:
                result -= 2;
                break;
            case 66:
                result -= 3;
                break;
            case 70:
                result -= 4;
                break;
            case 110:
                result -= 5;
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }
}
