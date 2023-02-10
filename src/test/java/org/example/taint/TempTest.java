package org.testcases.taint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TempTest {
    ///region Test suites for executable org.testcases.taint.Temp.fooCaller
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFooCaller1")
    public void testFooCaller1() {
        Temp temp = new Temp();
        
        assertThrows(ArithmeticException.class, () -> temp.fooCaller());
    }
    ///endregion
    
    ///endregion
}

