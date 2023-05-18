package org.example.checks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;

public final class ExampleTest {
    ///region Test suites for executable org.example.checks.Example.doGet
    
    ///region SYMBOLIC EXECUTION: ERROR SUITE for method doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.invokes {@link javax.servlet.http.HttpServletRequest#getParameter(java.lang.String)}
 * @utbot.throwsException {@link java.lang.NullPointerException} in: String s = req.getParameter("name");
 *  */
    @Test
    @DisplayName("doGet: s = req.getParameter(\"name\") : True -> ThrowNullPointerException")
    public void testDoGet_HttpServletRequestGetParameter() throws IOException  {
        Example example = new Example();
        
        /* This test fails because method [org.example.checks.Example.doGet] produces [java.lang.NullPointerException]
            org.example.checks.Example.doGet(Example.java:26) */
        example.doGet(null, null);
    }
    ///endregion
    
    ///region OTHER: ERROR SUITE for method doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    
    @Test
    public void testDoGet1() throws IOException  {
        Example example = new Example();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("");
        
        /* This test fails because method [org.example.checks.Example.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.checks.Example.doGet(Example.java:26) */
        example.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet2() throws IOException  {
        Example example = new Example();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("");
        
        /* This test fails because method [org.example.checks.Example.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.checks.Example.doGet(Example.java:26) */
        example.doGet(httpServletRequestMock, null);
    }
    ///endregion
    
    ///endregion
}

