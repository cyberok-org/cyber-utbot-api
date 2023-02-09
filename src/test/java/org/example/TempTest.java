package org.testcases.temp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TempTest {
    ///region Test suites for executable org.testcases.temp.Temp.foo
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo1")
    public void testFoo1() {
        Temp temp = new Temp();
        
        assertThrows(ArithmeticException.class, () -> temp.foo(0, 1));
    }
    ///endregion
    
    ///endregion
}

