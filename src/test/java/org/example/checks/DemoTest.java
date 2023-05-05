package org.example.checks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.io.IOException;

import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class DemoTest {
    ///region Test suites for executable org.example.checks.Demo.example
    
    ///region SYMBOLIC EXECUTION: ERROR SUITE for method example(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    
    /**
    @utbot.classUnderTest {@link Demo}
 * @utbot.methodUnderTest {@link org.example.checks.Demo#example(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.executesCondition {@code (header.equals("some value")): True}
 * @utbot.executesCondition {@code (request.getHeader("header2").equals("some value2")): True}
 * @utbot.executesCondition {@code (theCookies[5].getValue().equals("cookie value")): True}
 * @utbot.executesCondition {@code (theCookies[5].getName().equals("cookie name")): True}
 * @utbot.invokes {@link javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)}
 * @utbot.invokes {@link java.lang.String#equals(java.lang.Object)}
 * @utbot.invokes {@link javax.servlet.http.HttpServletRequest#getCookies()}
 * @utbot.invokes {@link javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)}
 * @utbot.invokes {@link java.lang.String#equals(java.lang.Object)}
 * @utbot.invokes {@link javax.servlet.http.Cookie#getValue()}
 * @utbot.invokes {@link java.lang.String#equals(java.lang.Object)}
 * @utbot.invokes {@link javax.servlet.http.Cookie#getName()}
 * @utbot.invokes {@link java.lang.String#equals(java.lang.Object)}
 * @utbot.invokes {@link javax.servlet.http.Cookie#getPath()}
 * @utbot.invokes {@link javax.servlet.http.HttpServletRequest#getParameter(java.lang.String)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: java.io.File fileTarget = new java.io.File(path);
 *  */
    @Test
    @DisplayName("example: fileTarget = new java.io.File(path) -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file reading")
    public void testExample_TheCookies5GetNameEquals() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Demo demo = new Demo();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getHeader("header"))).thenReturn("some value");
            (org.mockito.Mockito.when(httpServletRequestMock.getHeader("header2"))).thenReturn("some value2");
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter(""))).thenReturn("../etc/passwd");
            javax.servlet.http.Cookie[] cookieArray = new javax.servlet.http.Cookie[10];
            Cookie cookieMock = mock(Cookie.class);
            (org.mockito.Mockito.when(cookieMock.getName())).thenReturn("cookie name");
            (org.mockito.Mockito.when(cookieMock.getValue())).thenReturn("cookie value");
            cookieArray[5] = cookieMock;
            Cookie cookieMock_9 = org.mockito.Mockito.mock(Cookie.class);
            (org.mockito.Mockito.when(cookieMock_9.getPath())).thenReturn("");
            cookieArray[9] = cookieMock_9;
            (when(httpServletRequestMock.getCookies())).thenReturn(cookieArray);

            /* This test fails because method [org.example.checks.Demo.example] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:227)
                org.example.checks.Demo.example(Demo.java:11) */
            demo.example(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    ///endregion
    
    ///endregion
}

