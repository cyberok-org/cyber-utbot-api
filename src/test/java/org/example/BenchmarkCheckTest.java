package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class BenchmarkCheckTest {
    ///region Test suites for executable org.example.BenchmarkCheck.example
    
    
    @Test
    public void testExample1() {
        BenchmarkCheck benchmarkCheck = new BenchmarkCheck();
        
        /* This test fails because method [org.example.BenchmarkCheck.example] produces [java.lang.NullPointerException]
            org.example.BenchmarkCheck.example(BenchmarkCheck.java:17) */
        benchmarkCheck.example(null, null);
    }
    
    @Test
    public void testExample2() {
        BenchmarkCheck benchmarkCheck = new BenchmarkCheck();
        String string = "                              ";
        
        int actual = benchmarkCheck.example(string, null);
        
        assertEquals(13, actual);
    }
    
    @Test
    public void testExample3() {
        BenchmarkCheck benchmarkCheck = new BenchmarkCheck();
        String string = "v     ";
        
        int actual = benchmarkCheck.example(string, null);
        
        assertEquals(13, actual);
    }
    
    @Test
    public void testExample4() {
        BenchmarkCheck benchmarkCheck = new BenchmarkCheck();
        String string = "value2";
        
        int actual = benchmarkCheck.example(string, null);
        
        assertEquals(0, actual);
    }
    
    @Test
    public void testExample5() {
        BenchmarkCheck benchmarkCheck = new BenchmarkCheck();
        String string = "../etc/passwd";
        
        int actual = benchmarkCheck.example(string, null);
        
        assertEquals(2, actual);
    }
    
    ///endregion
}

