package org.testcases.formalGen;

// @author(pogrebnoijak)
// @date(19/12/2022)
// @description(This is an design example, it does not show anything worthwhile)

// @tag("if", {"significance": "important", "condition": "difficult"})`
// @tag("overflow")`
// @tag("arithmetic", {"operators": {"+", "*"}, "significance": "insignificant"})
// @tag("simple")`

public class Simple {
    public int fun(int x, int y) {
        int a = x;
        if (x > 0 && x * 2 < 0) {
            a = y;
        }
        return a + x;
    }
}
