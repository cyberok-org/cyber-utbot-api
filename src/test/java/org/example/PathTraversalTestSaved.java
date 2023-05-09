package org.example;

import org.example.inter.PathTraversal;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class PathTraversalTestSaved {
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
        String string = "";
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(string);
        
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet3() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        String string = "";
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(string);
        
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    
    ///region OTHER: SUCCESSFUL EXECUTIONS for method doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    
    @Test
    public void testDoGet21() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        String string = "";
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(string);
        
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet31() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        String string = "";
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(string);
        
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    ///endregion
    
    ///endregion
}

