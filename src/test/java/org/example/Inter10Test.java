package org.example.inter;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class Inter10Test {
    ///region Test suites for executable org.example.inter.Inter10.doGet
    
    
    @Test
    public void testDoGet1() throws IOException  {
        Inter10 inter10 = new Inter10();
        
        /* This test fails because method [org.example.inter.Inter10.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter10.doGet(Inter10.java:40) */
        inter10.doGet(null, null);
    }
    
    @Test
    public void testDoGet2() throws IOException  {
        Inter10 inter10 = new Inter10();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        
        /* This test fails because method [org.example.inter.Inter10.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter10.doGet(Inter10.java:45) */
        inter10.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet3() throws IOException  {
        Inter10 inter10 = new Inter10();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
        (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter10.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter10.doGet(Inter10.java:46) */
        inter10.doGet(httpServletRequestMock, httpServletResponseMock);
    }
    
    @Test
    public void testDoGet4() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter10 inter10 = new Inter10();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter10.doGet] produces [java.lang.IllegalStateException: Could not invoke public void java.io.PrintWriter.println(java.lang.String) on java.io.PrintWriter@44e4d2b4 with arguments [null]]
                org.mockito.internal.util.reflection.InstrumentationMemberAccessor.invoke(InstrumentationMemberAccessor.java:252)
                org.mockito.internal.util.reflection.ModuleMemberAccessor.invoke(ModuleMemberAccessor.java:55)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice.tryInvoke(MockMethodAdvice.java:333)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice.access$500(MockMethodAdvice.java:60)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice$RealMethodCall.invoke(MockMethodAdvice.java:253)
                org.mockito.internal.invocation.InterceptedInvocation.callRealMethod(InterceptedInvocation.java:142)
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.mockito.internal.handler.MockHandlerImpl.handle(MockHandlerImpl.java:110)
                org.mockito.internal.handler.NullResultGuardian.handle(NullResultGuardian.java:29)
                org.mockito.internal.handler.InvocationNotifierHandler.handle(InvocationNotifierHandler.java:34)
                org.mockito.internal.creation.bytebuddy.MockMethodInterceptor.doIntercept(MockMethodInterceptor.java:82)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice.handle(MockMethodAdvice.java:151)
                java.base/java.io.PrintWriter.println(PrintWriter.java:821)
                org.example.inter.Inter10.doGet(Inter10.java:46) */
            inter10.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter10.getDescription
    
    
    @Test
    public void testGetDescription1() {
        Inter10 inter10 = new Inter10();
        
        String actual = inter10.getDescription();
        
        String expected = "more complex object sensitivity";
        assertEquals(expected, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter10.foo
    
    
    @Test
    public void testFoo1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        Inter10 inter10 = new Inter10();
        
        Class inter10Clazz = Class.forName("org.example.inter.Inter10");
        Class stringType = Class.forName("java.lang.String");
        Method fooMethod = inter10Clazz.getDeclaredMethod("foo", stringType);
        fooMethod.setAccessible(true);
        java.lang.Object[] fooMethodArguments = new java.lang.Object[1];
        fooMethodArguments[0] = ((Object) null);
        String actual = ((String) fooMethod.invoke(inter10, fooMethodArguments));
        
        assertNull(actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter10.getVulnerabilityCount
    
    
    @Test
    public void testGetVulnerabilityCount1() {
        Inter10 inter10 = new Inter10();
        
        int actual = inter10.getVulnerabilityCount();
        
        assertEquals(1, actual);
    }
    
    ///endregion
}

