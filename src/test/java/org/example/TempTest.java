package org.testcases.temp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TempTest {
    ///region Test suites for executable org.testcases.temp.Temp.main
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testMain1")
    public void testMain1() {
        Temp.main(null);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.temp.Temp.vulnerable
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testVulnerable1")
    public void testVulnerable1() {
        Temp temp = new Temp();
        
        temp.vulnerable(-255);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.temp.Temp.fooCaller
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFooCaller1")
    public void testFooCaller1() {
        Temp temp = new Temp();
        
        assertThrows(ArithmeticException.class, () -> temp.fooCaller());
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.temp.Temp.bar
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testBar1")
    public void testBar1() {
        Temp temp = new Temp();
        
        int actual = temp.bar();
        
        assertEquals(-10, actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.temp.Temp.foo
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo1")
    public void testFoo1() {
        Temp temp = new Temp();
        
        int actual = temp.foo(-224, -224);
        
        assertEquals(50176, actual);
    }
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo2")
    public void testFoo2() {
        Temp temp = new Temp();
        
        assertThrows(ArithmeticException.class, () -> temp.foo(0, 1));
    }
    ///endregion
    
    ///endregion
}

