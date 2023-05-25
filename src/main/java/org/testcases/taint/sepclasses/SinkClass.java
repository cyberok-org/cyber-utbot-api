package org.testcases.taint.sepclasses;

import java.util.Random;

public class SinkClass {
    public void foo(String arg1, String arg2, String arg3) {
        boolean rand = new Random().nextBoolean();
        if (arg3.equals("hehe")) {
//            SourceClass src = new SourceClass(true);
//            if (arg1.equals("arg1")) {
//                String arg = src.bar();
//                inBetween(true, arg2, arg3); // ok
//            } else {
//                String arg = src.bar();
//                inBetween(true, arg3, arg); // dangerous
//            }
        } else {
            SourceClass src2 = new SourceClass(true);
            String arg = src2.source(arg3.length(), 10); // len should be > 20
            sink1(arg, "", "fff");
//            inBetween(arg2.equals("..."), arg2, arg); // danger
        }
    }

    public void inBetween(boolean b, String arg1, String arg2) { // second one is bad
        if (b) {
            sink(arg1, arg2);
        }
        else {
            sink1(arg1, arg2, "fffdnglwr");
            sink(arg2, "ok");
        }
    }

    void sink(String arg1, String arg2) {
    } // second one is bad

    void sink1(String arg1, String arg2, String arg3) {
    }
}
