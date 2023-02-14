package org.testcases.taint;

import org.junit.jupiter.api.Test;

public final class SeparateTracesTest {
    ///region Test suites for executable org.testcases.taint.SeparateTraces.bar
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testBar1")
    public void testBar1() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.bar(6);
    }
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testBar2")
    public void testBar2() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.bar(5);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.SeparateTraces.foo
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo1")
    public void testFoo1() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.foo(14);
    }
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo2")
    public void testFoo2() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.foo(-1919);
    }
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo3")
    public void testFoo3() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.foo(0);
    }
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo4")
    public void testFoo4() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.foo(-189);
    }
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("testFoo5")
    public void testFoo5() {
        SeparateTraces separateTraces = new SeparateTraces();
        
        separateTraces.foo(1);
    }
    ///endregion
    
    ///endregion
}

