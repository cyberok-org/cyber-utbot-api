package org.testcases.taint;

public class SeparateTraces {

    public String source1() {
        return "aaa";
    }

    public String source2() {
        return "bbb";
    }

    public void foo(int a) {
        if (a > 13) {
            sink1(source1());
//            int val = source2();
//            sink2(24, val);
        } else if (a > 0) {
            sink2("fd", "bbb");
        } else if (a == -1919) {
            sink1(source1());
        } else if (a == -189) {
            System.out.println("...");
        } else {
            sink2(source2(), "hello");
        }
    }

    public void bar(int i) {
        if (i > 5) {
            String x = source2();
            sink2(x, "ff");
        }
    }

    public void sink1(String s) {
    }

    public void sink2(String x, String y) {
    }
}
