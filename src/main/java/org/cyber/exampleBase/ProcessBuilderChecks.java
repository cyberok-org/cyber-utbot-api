package org.cyber.exampleBase;

import java.util.Objects;

public class ProcessBuilderChecks {
    boolean checkMany(String s) {
        return false;
    }

    boolean checkSimple(String s) {
        return Objects.equals(s, "/bin/sh");
    }
}
