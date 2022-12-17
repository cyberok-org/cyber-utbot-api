package org.testcases.structures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class SimpleTest {
    ///region Test suites for executable org.testcases.structures.list.Simple.fun
    
    ///region
    
    @Test
    public void testFun1() {
        Simple simple = new Simple();
        
        boolean actual = simple.fun(true);
        
        assertTrue(actual);
    }
    ///endregion
    
    ///endregion
}

