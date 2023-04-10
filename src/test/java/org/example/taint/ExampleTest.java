package org.testcases.taint;

import org.junit.jupiter.api.Test;

public final class ExampleTest {
    ///region Test suites for executable org.testcases.taint.Example.source
    

    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.Example.bar
    
    ///region
    
    @Test
    @org.junit.jupiter.api.Disabled(value = "Disabled due to possible JVM crash")
    public void testBar1() {
        Example example = new Example();
        
        // This invocation possibly crashes JVM
        example.bar(-255);
    }
    ///endregion
    
    ///endregion
}

