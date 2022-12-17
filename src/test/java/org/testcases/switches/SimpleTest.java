package org.testcases.switches;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class SimpleTest {
    ///region Test suites for executable org.testcases.switches.Simple.fun
    
    ///region
    
    @Test
    public void testFun1() {
        Simple simple = new Simple();
        
        int actual = simple.fun(64);
        
        assertEquals(64, actual);
    }
    
    @Test
    public void testFun2() {
        Simple simple = new Simple();
        
        int actual = simple.fun(2);
        
        assertEquals(0, actual);
    }
    
    @Test
    public void testFun3() {
        Simple simple = new Simple();
        
        int actual = simple.fun(3);
        
        assertEquals(2, actual);
    }
    
    @Test
    public void testFun4() {
        Simple simple = new Simple();
        
        int actual = simple.fun(1);
        
        assertEquals(2, actual);
    }
    ///endregion
    
    ///endregion
}

