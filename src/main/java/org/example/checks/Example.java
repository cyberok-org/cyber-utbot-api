package org.example.checks;

import java.io.File;

public class Example {
//    static String dir = System.getProperty("user.dir");

//    public boolean example(String s) {
//        System.getProperty(s);
//        if (s.equals("1")) {
//            return true;
//        }
//        return false;
//    }

    public boolean example(String s) {
        return File.separator.equals(s);
    }
}
