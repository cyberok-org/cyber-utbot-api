package org.example;

public class Interprocedural {
    int some;
    int smth;

    public int lookupSwitch(int x) {
        if (x == 4) {
            return 2;
        }
        return 0;
    }

    public void foo(int y) {
        if (smth == 10 && y == 2) {
            lookupSwitch(4);
        }
        else {
            lookupSwitch(8);
        }
    }

    public void bar() {
        if (some == 2) {
            foo(2);
        }
        else {
            foo(1);
        }
    }
}
