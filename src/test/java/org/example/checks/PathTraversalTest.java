package org.example.inter;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class PathTraversalTest {
    ///region Test suites for executable org.example.inter.PathTraversal.doGet
    
    
    @Test
    public void testDoGet1() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        
        /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [java.lang.NullPointerException]
            org.example.inter.PathTraversal.doGet(PathTraversal.java:39) */
        pathTraversal.doGet(null, null);
    }
    
    @Test
    public void testDoGet2() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.inter.PathTraversal.doGet(PathTraversal.java:39) */
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet3() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.inter.PathTraversal.doGet(PathTraversal.java:39) */
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    
    ///region OTHER: ERROR SUITE for method doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    
    @Test
    public void testDoGet21() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.inter.PathTraversal.doGet(PathTraversal.java:39) */
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet31() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.inter.PathTraversal.doGet(PathTraversal.java:39) */
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.PathTraversal.getDescription
    
    
    @Test
    public void testGetDescription1() {
        PathTraversal pathTraversal = new PathTraversal();
        
        String actual = pathTraversal.getDescription();
        
        String expected = "path traversal";
        assertEquals(expected, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.PathTraversal.getVulnerabilityCount
    
    
    @Test
    public void testGetVulnerabilityCount1() {
        PathTraversal pathTraversal = new PathTraversal();
        
        int actual = pathTraversal.getVulnerabilityCount();
        
        assertEquals(2, actual);
    }
    
    ///endregion
}

