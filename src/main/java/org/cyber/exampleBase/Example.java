package org.cyber.exampleBase;

import java.util.Objects;

public class Example {
    boolean fun1(String s) {
        if (Objects.equals(s, "100")) {
            return true;
        }
        return false;
    }

    boolean fun2(String s) {
        if (Objects.equals(s, "500")) {
            return true;
        }
        return false;
    }

    boolean fun3(String s) {
        if (Objects.equals(s, "1000")) {
            return true;
        }
        return false;
    }

    boolean fun4(String s) {
        if (Objects.equals(s, "505")) {
            return true;
        }
        return false;
    }
}
