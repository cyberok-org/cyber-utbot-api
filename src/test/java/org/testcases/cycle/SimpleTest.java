package org.testcases.cycle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class SimpleTest {
    ///region Test suites for executable org.testcases.cycle.Simple.fun
    
    ///region
    
    @Test
    public void testFun1() {
        Simple simple = new Simple();
        
        boolean actual = simple.fun(0);
        
        assertTrue(actual);
    }
    
    @Test
    public void testFun2() {
        Simple simple = new Simple();
        
        boolean actual = simple.fun(1);
        
        assertTrue(actual);
    }
    
    @Test
    public void testFun3() {
        Simple simple = new Simple();
        
        boolean actual = simple.fun(-255);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

