package org.testcases.taint;

import org.junit.jupiter.api.Test;

public final class ExampleTest {
    ///region Test suites for executable org.testcases.taint.Example.launch
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("Taint example")
    public void testLaunch1() {
        Example example = new Example();
        
        example.launch();
    }
    ///endregion
    
    ///endregion
}

