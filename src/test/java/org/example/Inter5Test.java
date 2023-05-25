package org.example.inter;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Inter5Test {
    ///region Test suites for executable org.example.inter.Inter5.id
    
    
    @Test
    public void testId1() throws Throwable  {
        Inter5 inter5 = new Inter5();
        
        /* This test fails because method [org.example.inter.Inter5.id] produces [java.lang.NullPointerException]
            org.example.inter.Inter5.id(Inter5.java:49) */
        Class inter5Clazz = Class.forName("org.example.inter.Inter5");
        Class stringType = Class.forName("java.lang.String");
        Method idMethod = inter5Clazz.getDeclaredMethod("id", stringType);
        idMethod.setAccessible(true);
        java.lang.Object[] idMethodArguments = new java.lang.Object[1];
        idMethodArguments[0] = ((Object) null);
        try {
            idMethod.invoke(inter5, idMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter5.doGet
    
    
    @Test
    public void testDoGet1() throws IOException  {
        Inter5 inter5 = new Inter5();
        
        /* This test fails because method [org.example.inter.Inter5.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter5.doGet(Inter5.java:38) */
        inter5.doGet(null, null);
    }
    
    @Test
    public void testDoGet2() throws IOException  {
        Inter5 inter5 = new Inter5();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        
        /* This test fails because method [org.example.inter.Inter5.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter5.id(Inter5.java:49)
            org.example.inter.Inter5.doGet(Inter5.java:40) */
        inter5.doGet(httpServletRequestMock, null);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter5.getDescription
    
    
    @Test
    public void testGetDescription1() {
        Inter5 inter5 = new Inter5();
        
        String actual = inter5.getDescription();
        
        String expected = "store stuff in a field";
        assertEquals(expected, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter5.getVulnerabilityCount
    
    
    @Test
    public void testGetVulnerabilityCount1() {
        Inter5 inter5 = new Inter5();
        
        int actual = inter5.getVulnerabilityCount();
        
        assertEquals(2, actual);
    }
    
    ///endregion
}

