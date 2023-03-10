package org.testcases.taint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ExampleTest {
    ///region Test suites for executable org.testcases.taint.Example.source
    
    ///region
    
    @Test
    public void testSource1() {
        Example example = new Example();
        
        String actual = example.source();
        
        String expected = "dangerous data";
        assertEquals(expected, actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.Example.sink
    
    ///region
    
    @Test
    public void testSink1() {
        Example example = new Example();
        
        example.sink(null);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.Example.launch
    
    ///region
    
    @Test
    public void testLaunch1() {
        Example example = new Example();
        
        example.launch(0);
    }
    
    @Test
    public void testLaunch2() {
        Example example = new Example();
        
        example.launch(1);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.Example.foo
    
    ///region
    
    @Test
    public void testFoo1() {
        Example example = new Example();
        
        /* This test fails because method [org.testcases.taint.Example.foo] produces [java.lang.NullPointerException]
            org.testcases.taint.Example.foo(Example.java:5) */
        example.foo(null, null);
    }
    
    @Test
    public void testFoo2() {
        Example example = new Example();
        String string = "";
        
        example.foo(string, null);
    }
    
    @Test
    public void testFoo3() {
        Example example = new Example();
        String string = "";
        
        example.foo(string, string);
    }
    ///endregion
    
    ///endregion
}

