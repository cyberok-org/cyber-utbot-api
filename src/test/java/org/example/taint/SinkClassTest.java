package org.testcases.taint.sepclasses;

import org.junit.jupiter.api.Test;

public final class SinkClassTest {
    ///region Test suites for executable org.testcases.taint.sepclasses.SinkClass.foo
    
    ///region
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("for testing separate classes")
    public void testFoo1() {
        SinkClass sinkClass = new SinkClass();
        String string = "...";
        String string1 = "                     ";
        
        sinkClass.foo(null, string, string1);
    }
    
    @Test
    @org.cyber.utils.VulnerabilityInfo("for testing separate classes")
    public void testFoo2() {
        SinkClass sinkClass = new SinkClass();
        String string = " ";
        String string1 = "arg2";
        
        sinkClass.foo(string, string1, null);
    }
    
    @Test
    public void testFoo3() {
        SinkClass sinkClass = new SinkClass();
        String string = "arg1";
        String string1 = "arg2";
        String string2 = "\u0000aaaggggg";
        
        sinkClass.foo(string, string1, string2);
    }
    ///endregion
    
    ///region OTHER: SUCCESSFUL EXECUTIONS for method foo(java.lang.String, java.lang.String, java.lang.String)
    
    @Test
    public void testFoo31() {
        SinkClass sinkClass = new SinkClass();
        String string = "arg1";
        String string1 = "arg2";
        String string2 = "\u0000aaaggggg";
        
        sinkClass.foo(string, string1, string2);
    }
    ///endregion
    
    ///endregion
}

