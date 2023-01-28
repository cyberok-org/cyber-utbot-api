package org.testcases.temp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TempTest {
    ///region Test suites for executable org.testcases.temp.Temp.foo
    
    ///region
    
    @Test
    public void testFoo1() {
        int actual = Temp.foo(1, 0);
        
        assertEquals(0, actual);
    }
    ///endregion
    
    ///region Errors report for foo
    
    public void testFoo_errors()
     {
        // Couldn't generate some tests. List of errors:
        // 
        // 1 occurrences of:
        // Could not initialize class org.utbot.common.Reflection
        
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.temp.Temp.bar
    
    ///region
    
    @Test
    public void testBar1() {
        int actual = Temp.bar();
        
        assertEquals(-10, actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.temp.Temp.vulnerable
    
    ///region Errors report for vulnerable
    
    public void testVulnerable_errors()
     {
        // Couldn't generate some tests. List of errors:
        // 
        // 1 occurrences of:
        // Could not initialize class org.utbot.common.Reflection
        
    }
    ///endregion
    
    ///endregion
}

