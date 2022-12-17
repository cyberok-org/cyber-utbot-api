package org.testcases.structures.list;

import java.util.Arrays;
import java.util.List;


public class Simple {
    public boolean fun(boolean x) {
        List<Boolean> bools = Arrays.asList(false, false, false, false, x);
        return bools.contains(true);
    }
}
