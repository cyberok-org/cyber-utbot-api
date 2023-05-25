package org.example;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

public final class BenchmarkTest00177Test {
    ///region Test suites for executable org.example.BenchmarkTest00177.doPost
    
    
    @Test
    public void testDoPost1() throws IOException  {
        BenchmarkTest00177 benchmarkTest00177 = new BenchmarkTest00177();
        
        /* This test fails because method [org.example.BenchmarkTest00177.doPost] produces [java.lang.NullPointerException]
            org.example.BenchmarkTest00177.doPost(BenchmarkTest00177.java:40) */
        benchmarkTest00177.doPost(null, null);
    }
    
    @Test
    public void testDoPost2() throws IOException  {
        BenchmarkTest00177 benchmarkTest00177 = new BenchmarkTest00177();
        HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
        ((doNothing()).when(httpServletResponseMock)).setContentType(any());
        
        /* This test fails because method [org.example.BenchmarkTest00177.doPost] produces [java.lang.NullPointerException]
            org.example.BenchmarkTest00177.doPost(BenchmarkTest00177.java:42) */
        benchmarkTest00177.doPost(null, httpServletResponseMock);
    }
    
    ///endregion
}

