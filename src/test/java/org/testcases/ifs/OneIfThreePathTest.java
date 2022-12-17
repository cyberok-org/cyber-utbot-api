package org.testcases.ifs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class OneIfThreePathTest {
    ///region Test suites for executable org.testcases.ifs.OneIfThreePath.fun
    
    ///region
    
    @Test
    public void testFun1() {
        OneIfThreePath oneIfThreePath = new OneIfThreePath();
        
        boolean actual = oneIfThreePath.fun(-255, 0);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun2() {
        OneIfThreePath oneIfThreePath = new OneIfThreePath();
        
        boolean actual = oneIfThreePath.fun(16, 1);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun3() {
        OneIfThreePath oneIfThreePath = new OneIfThreePath();
        
        boolean actual = oneIfThreePath.fun(-1, 1);
        
        assertTrue(actual);
    }
    ///endregion
    
    ///endregion
}

