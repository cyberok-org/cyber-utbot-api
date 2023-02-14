package org.testcases.taint;

public class SeparateTraces {

    public int source1() {
        return 10;
    }

    public int source2() {
        return 44;
    }

    public void foo(int a) {
        if (a > 13) {
            sink1(source1());
//            int val = source2();
//            sink2(24, val);
        } else if (a > 0) {
            sink2(1, 2);
        } else if (a == -1919) {
            sink1(source1());
        } else if (a == -189) {
            System.out.println("...");
        } else {
            int smth = sink2(source2(), 44);
        }
    }

    public void bar(int i) {
        if (i > 5) {
            int x = source1();
            sink1(x);
        }
    }

    public void smth() {
        System.out.println("bar");
        int x = source1();
    }

    public void sink1(int s) {
    }

    public int sink2(int x, int y) {
        return x + y;
    }
}
