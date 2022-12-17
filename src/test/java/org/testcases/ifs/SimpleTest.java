package org.testcases.ifs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class SimpleTest {
    ///region Test suites for executable org.testcases.ifs.Simple.oneIf
    
    ///region
    
    @Test
    public void testOneIf1() {
        Simple simple = new Simple();
        
        int actual = simple.oneIf(5, -255);
        
        assertEquals(-510, actual);
    }
    
    @Test
    public void testOneIf2() {
        Simple simple = new Simple();
        
        int actual = simple.oneIf(-255, -255);
        
        assertEquals(-255, actual);
    }
    ///endregion
    
    ///endregion
}

