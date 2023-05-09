package org.example.inter;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;

public final class Inter1Test {
    ///region Test suites for executable org.example.inter.Inter1.doGet
    
    
    @Test
    public void testDoGet1() throws IOException  {
        Inter1 inter1 = new Inter1();
        
        /* This test fails because method [org.example.inter.Inter1.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter1.doGet(Inter1.java:12) */
        inter1.doGet(null, null);
    }
    
    @Test
    public void testDoGet2() throws IOException  {
        Inter1 inter1 = new Inter1();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        
        /* This test fails because method [org.example.inter.Inter1.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter1.doGet(Inter1.java:17) */
        inter1.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet3() throws IOException  {
        Inter1 inter1 = new Inter1();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
        (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter1.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter1.doGet(Inter1.java:18) */
        inter1.doGet(httpServletRequestMock, httpServletResponseMock);
    }
    
    @Test
    public void testDoGet4() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter1 inter1 = new Inter1();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) "smth"));
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter1.doGet] produces [java.lang.IllegalStateException: Could not invoke public void java.io.PrintWriter.println(java.lang.String) on java.io.PrintWriter@35eeb676 with arguments [null]]
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
                org.example.inter.Inter1.doGet(Inter1.java:18) */
            inter1.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    ///endregion
}

