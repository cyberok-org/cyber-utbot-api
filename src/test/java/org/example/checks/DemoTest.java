package org.example.checks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mock;

public final class DemoTest {
    ///region Test suites for executable org.example.checks.Demo.demo
    
    ///region SYMBOLIC EXECUTION: ERROR SUITE for method demo(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    
    /**
    @utbot.classUnderTest {@link Demo}
 * @utbot.methodUnderTest {@link org.example.checks.Demo#demo(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link java.lang.NullPointerException} in: String path = request.getParameter("ggg");
 *  */
    @Test
    @DisplayName("demo: path = request.getParameter(\"ggg\") : True -> ThrowNullPointerException")
    public void testDemo_ThrowNullPointerException() throws IOException  {
        Demo demo = new Demo();
        
        /* This test fails because method [org.example.checks.Demo.demo] produces [java.lang.NullPointerException]
            org.example.checks.Demo.demo(Demo.java:10) */
        demo.demo(null, null);
    }
    
    /**
    @utbot.classUnderTest {@link Demo}
 * @utbot.methodUnderTest {@link org.example.checks.Demo#demo(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.executesCondition {@code (header.equals("some value")): False}
 * @utbot.invokes {@link javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)}
 * @utbot.invokes {@link java.lang.String#equals(java.lang.Object)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: java.io.File fileTarget = new java.io.File(path);
 *  */
    @Test
    @DisplayName("demo: fileTarget = new java.io.File(path) -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDemo_NotHeaderEquals() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class,
                    (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Demo demo = new Demo();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getHeader("header")))
                    .thenReturn("");
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("path")))
                    .thenReturn("../../../../../../etc/passwd");
            demo.demo(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    ///endregion
    
    ///endregion
}

