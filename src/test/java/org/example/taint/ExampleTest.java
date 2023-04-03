package org.testcases.taint;

import org.junit.jupiter.api.Test;

public final class ExampleTest {
    ///region Test suites for executable org.testcases.taint.Example.source
    
    ///region
    
    @Test
    @org.junit.jupiter.api.Disabled(value = "Disabled due to possible JVM crash")
    public void testSource1() {
        Example example = new Example();
        
        // This invocation possibly crashes JVM
        example.source();
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.Example.sink
    
    ///region
    
    @Test
    @org.junit.jupiter.api.Disabled(value = "Disabled due to possible JVM crash")
    public void testSink1() {
        Example example = new Example();
        
        // This invocation possibly crashes JVM
        example.sink(null);
    }
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

