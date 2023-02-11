package org.testcases.taint;

import org.junit.jupiter.api.Test;

public final class SeparateTracesTest {
    ///region Test suites for executable org.testcases.taint.SeparateTraces.foo
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo1")
    public void testFoo1() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.foo(1);
    }
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo2")
    public void testFoo2() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.foo(0);
    }
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo3")
    public void testFoo3() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.foo(14);
    }
    ///endregion
    
    ///endregion
}

