package org.testcases.taint;

public class Example {
    public void foo(String x, String y) {
        if (!x.equals(y)) {
            sink(x);
        }
    }
    public void sink(String s) { }

    public String source() {
        return "dangerous data";
    }
    public void launch() {
        foo(source(), "...");
    }
}

