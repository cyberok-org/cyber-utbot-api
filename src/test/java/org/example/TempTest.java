package org.testcases.temp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TempTest {
    ///region Test suites for executable org.testcases.temp.Temp.foo
    
    ///region
    
    @Test
    public void testFoo1() {
        assertThrows(ArithmeticException.class, () -> Temp.foo(255, 256));
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
    
    ///region
    
    @Test
    public void testVulnerable1() {
        Temp.vulnerable(-255);
    }
    ///endregion
    
    ///endregion
}

