package org.testcases.cycle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class ForLimitTest {
    ///region Test suites for executable org.testcases.cycle.ForLimit.fun
    
    ///region
    
    @Test
    public void testFun1() {
        ForLimit forLimit = new ForLimit();
        
        boolean actual = forLimit.fun(23);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun2() {
        ForLimit forLimit = new ForLimit();
        
        boolean actual = forLimit.fun(1);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun3() {
        ForLimit forLimit = new ForLimit();
        
        boolean actual = forLimit.fun(0);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

