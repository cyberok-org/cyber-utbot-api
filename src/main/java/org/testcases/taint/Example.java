package org.testcases.taint;

public class Example {
    public String source() {
        return "malicious data";
    }
    public void sink(String s) {
        // something unsafe
    }
    public void bar(int x) {
        String s = source();
    }
    public void foo() {
        String source = source();
        sink(source); // dangerous!
    }
}

