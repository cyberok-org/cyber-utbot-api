package org.testcases.taint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ExampleTest {
    ///region Test suites for executable org.testcases.taint.Example.source
    
    
    @Test
    public void testSource1() {
        Example example = new Example();
        
        String actual = example.source();
        
        String expected = "malicious data";
        assertEquals(expected, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.Example.sink
    
    
    @Test
    public void testSink1() {
        Example example = new Example();
        
        example.sink(null);
    }
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.Example.foo
    
    
    @Test
    public void testFoo1() {
        Example example = new Example();
        
        example.foo();
    }
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.Example.bar
    
    
    @Test
    public void testBar1() {
        Example example = new Example();
        
        example.bar(-255);
    }
    
    ///endregion
}

