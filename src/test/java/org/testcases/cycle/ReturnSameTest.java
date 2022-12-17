package org.testcases.cycle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ReturnSameTest {
    ///region Test suites for executable org.testcases.cycle.ReturnSame.fun
    
    ///region
    
    @Test
    public void testFun1() {
        ReturnSame returnSame = new ReturnSame();
        
        int actual = returnSame.fun(0);
        
        assertEquals(0, actual);
    }
    
    @Test
    public void testFun2() {
        ReturnSame returnSame = new ReturnSame();
        
        int actual = returnSame.fun(1);
        
        assertEquals(1, actual);
    }
    
    @Test
    public void testFun3() {
        ReturnSame returnSame = new ReturnSame();
        
        int actual = returnSame.fun(101);
        
        assertEquals(101, actual);
    }
    ///endregion
    
    ///endregion
}

