package org.testcases.taint.sepclasses;


public class SourceClass {

    boolean bool = false;

    public SourceClass(boolean b) {
        bool = b;
    }

    public String bar() {
        if (bool) return source(12, 24);
        else return source(-24, 40);
    }

    String source(int i, int i1) {
        if (i + i1 > 30) return "dangerous";
        else return "ok";
    }
}
