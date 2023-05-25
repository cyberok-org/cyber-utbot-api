package org.testcases.taint;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;

import static org.mockito.Mockito.mock;

public final class BenchmarkTest00500Test {
    ///region Test suites for executable org.testcases.taint.BenchmarkTest00500.doPost

    ///region

    @Test
    public void testDoPost1() throws ServletException, IOException {
        BenchmarkTest00500 benchmarkTest00500 = new BenchmarkTest00500();
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.testcases.taint.BenchmarkTest00500.doPost] produces [java.lang.NullPointerException]
            org.testcases.taint.BenchmarkTest00500.doPost(BenchmarkTest00500.java:45) */
        benchmarkTest00500.doPost(requestMock, null);
    }
    ///endregion

    ///region Errors report for doPost

    public void testDoPost_errors() {
        // Couldn't generate some tests. List of errors:
        // 
        // 1 occurrences of:
        // Concrete execution failed

    }
    ///endregion

    ///endregion

    ///region Test suites for executable org.testcases.taint.BenchmarkTest00500.sink4

    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method sink4(java.util.Map)

    /**
     * @utbot.classUnderTest {@link BenchmarkTest00500}
     * @utbot.methodUnderTest {@link BenchmarkTest00500#sink4(Map)}
     */
    @Test
    @DisplayName("sink4: ")
    public void testSink4() {
        BenchmarkTest00500 benchmarkTest00500 = new BenchmarkTest00500();

        benchmarkTest00500.sink4(null);
    }
    ///endregion

    ///endregion
}
