package org.testcases.taint.sepclasses;

import java.util.Random;

public class SinkClass {
    public void foo(String arg1, String arg2, String arg3) {
        boolean rand = new Random().nextBoolean();
        if (rand) {
            SourceClass src = new SourceClass(new Random().nextBoolean());
            if (arg1.equals("arg1")) {
                String arg = src.bar();
                inBetween(new Random().nextBoolean(), arg2, arg3); // ok
            } else {
                String arg = src.bar();
                inBetween(new Random().nextBoolean(), arg, arg3); // dangerous
            }
        } else {
            SourceClass src2 = new SourceClass(new Random().nextBoolean());
            String arg = src2.source(new Random().nextInt(), 10); // rand should be > 20
            inBetween(arg2.equals("..."), arg2, arg); // danger
        }
    }

    public void inBetween(boolean b, String arg1, String arg2) { // second one is bad
        if (b) sink(arg1, arg2);
        else sink(arg2, "ok");
    }

    void sink(String arg1, String arg2) {
    } // second one is bad
}
