package org.testcases.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class OnePowTest {
    ///region Test suites for executable org.testcases.math.OnePow.fun
    
    ///region
    
    @Test
    public void testFun1() {
        OnePow onePow = new OnePow();
        
        boolean actual = onePow.fun(4);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun2() {
        OnePow onePow = new OnePow();
        
        boolean actual = onePow.fun(36);
        
        assertTrue(actual);
    }
    ///endregion
    
    ///endregion
}

