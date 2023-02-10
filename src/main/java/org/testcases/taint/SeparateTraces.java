package org.testcases.taint;

public class SeparateTraces {

    public String source1() {
        return "aaa";
    }

    public int source2() {
        return 44;
    }

    public void foo(int a) {
        sink1(source1());
        if (a > 13) {
            int val = source2();
            sink2(24, val);
        } else if (a > 0) {
            sink2(1, 2);
        } else {
            int smth = sink2(source2(), 44);
        }
    }

    public void bar() {
        System.out.println("bar");
        String x = source1();
    }

    public void sink1(String s) {
        System.out.println(s);
    }

    public int sink2(int x, int y) {
        return x + y;
    }
}
