package org.testcases.switches;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferentNumsTest {
    ///region Test suites for executable org.testcases.switches.DifferentNums.fun
    
    ///region
    
    @Test
    public void testFun1() {
        DifferentNums differentNums = new DifferentNums();
        
        int actual = differentNums.fun(-255);
        
        assertEquals(-1, actual);
    }
    
    @Test
    public void testFun2() {
        DifferentNums differentNums = new DifferentNums();
        
        int actual = differentNums.fun(255);
        
        assertEquals(2, actual);
    }
    
    @Test
    public void testFun3() {
        DifferentNums differentNums = new DifferentNums();
        
        int actual = differentNums.fun(Integer.MIN_VALUE);
        
        assertEquals(4, actual);
    }
    
    @Test
    public void testFun4() {
        DifferentNums differentNums = new DifferentNums();
        
        int actual = differentNums.fun(Integer.MAX_VALUE);
        
        assertEquals(1, actual);
    }
    
    @Test
    public void testFun5() {
        DifferentNums differentNums = new DifferentNums();
        
        int actual = differentNums.fun(-999999);
        
        assertEquals(3, actual);
    }
    ///endregion
    
    ///endregion
}

