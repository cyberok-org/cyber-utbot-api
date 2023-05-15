package org.example.inter;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletResponseWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Inter13Test {
    ///region Test suites for executable org.example.inter.Inter13.f
    
    /// Actual number of generated tests (69) exceeds per-method limit (50)
    /// The limit can be configured in '{HOME_DIR}/.utbot/settings.properties' with 'maxTestsPerMethod' property
    
    @Test
    public void testF1() throws Throwable  {
        Inter13 inter13 = new Inter13();
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@bf2524a with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            org.example.inter.Inter13.f(Inter13.java:49) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = noBodyResponseMock;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF2() throws Throwable  {
        Inter13 inter13 = new Inter13();
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.NullPointerException]
            org.example.inter.Inter13.f(Inter13.java:49) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Class servletResponseType = Class.forName("javax.servlet.ServletResponse");
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseType);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = ((Object) null);
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF3() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@4aef48fb with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                org.example.inter.Inter13.f(Inter13.java:49) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 0;
            fMethodArguments[2] = noBodyResponseMock;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF4() throws Throwable  {
        Inter13 inter13 = new Inter13();
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.NullPointerException]
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Class servletResponseType = Class.forName("javax.servlet.ServletResponse");
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseType);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 1;
        fMethodArguments[2] = ((Object) null);
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF5() throws Throwable  {
        Inter13 inter13 = new Inter13();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@45e58d2 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF6() throws Throwable  {
        Inter13 inter13 = new Inter13();
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@59f76961 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 1;
        fMethodArguments[2] = noBodyResponseMock;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF7() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@7adeac4b with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 0;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF8() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@781a19b9 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF9() throws Throwable  {
        Inter13 inter13 = new Inter13();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@67fb1f00 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 1;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF10() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@566ecbdb with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 0;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF11() throws Throwable  {
        Inter13 inter13 = new Inter13();
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@7c517cb7 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 2;
        fMethodArguments[2] = noBodyResponseMock;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF12() throws Throwable  {
        Inter13 inter13 = new Inter13();
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.NullPointerException]
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Class servletResponseType = Class.forName("javax.servlet.ServletResponse");
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseType);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 2;
        fMethodArguments[2] = ((Object) null);
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF13() throws Throwable  {
        Inter13 inter13 = new Inter13();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@48a48bc9 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF14() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@1ef21479 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 1;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF15() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@6687ff13 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF16() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@67ddbfe1 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 1;
            fMethodArguments[2] = noBodyResponseMock;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF17() throws Throwable  {
        Inter13 inter13 = new Inter13();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@37f2d933 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 2;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF18() throws Throwable  {
        Inter13 inter13 = new Inter13();
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.NullPointerException]
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Class servletResponseType = Class.forName("javax.servlet.ServletResponse");
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseType);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 3;
        fMethodArguments[2] = ((Object) null);
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF19() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@1d7aef21 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 0;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF20() throws Throwable  {
        Inter13 inter13 = new Inter13();
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@93dcbe6 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 3;
        fMethodArguments[2] = noBodyResponseMock;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF21() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@3e69583a with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 0;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF22() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@4282133e with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF23() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@255d19a1 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 1;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF24() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@4dabd858 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 2;
            fMethodArguments[2] = noBodyResponseMock;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF25() throws Throwable  {
        Inter13 inter13 = new Inter13();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@2e63d127 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 1;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF26() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper3 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper3, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper3);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@66a6e3d3 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 0;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF27() throws Throwable  {
        Inter13 inter13 = new Inter13();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@6899ad89 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 3;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF28() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@351b6949 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 1;
            fMethodArguments[2] = httpServletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF29() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@665e2df7 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 3;
            fMethodArguments[2] = noBodyResponseMock;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF30() throws Throwable  {
        Inter13 inter13 = new Inter13();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@5b09286f with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 2;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF31() throws Throwable  {
        Inter13 inter13 = new Inter13();
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.NullPointerException]
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Class servletResponseType = Class.forName("javax.servlet.ServletResponse");
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseType);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 4;
        fMethodArguments[2] = ((Object) null);
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF32() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@83eef8b with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 1;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF33() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@229a6d05 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 2;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF34() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@5556d737 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 3;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF35() throws Throwable  {
        Inter13 inter13 = new Inter13();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper3 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper3, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper3);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@6779ebfe with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF36() throws Throwable  {
        Inter13 inter13 = new Inter13();
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@4a66259d with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 4;
        fMethodArguments[2] = noBodyResponseMock;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF37() throws Throwable  {
        Inter13 inter13 = new Inter13();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@1fd12400 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 4;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF38() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@1e7c434c with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 2;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF39() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@6f674fdb with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 1;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF40() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@571d19b2 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 2;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF41() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper2 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(httpServletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper2);
            setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@5ebfee79 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 0;
            fMethodArguments[2] = httpServletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF42() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@318a62d6 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 1;
            fMethodArguments[2] = httpServletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF43() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@3261b7d6 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 2;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF44() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@58a8924e with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 2;
            fMethodArguments[2] = httpServletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF45() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@5a788068 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 2;
            fMethodArguments[2] = httpServletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF46() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@63432292 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 3;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF47() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter13 inter13 = new Inter13();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            
            /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@e9f20cb with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter13.f(Inter13.java:49)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47)
                org.example.inter.Inter13.f(Inter13.java:47) */
            Class inter13Clazz = Class.forName("org.example.inter.Inter13");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 4;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter13, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF48() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@249ea388 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 1;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF49() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper2 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper3 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper3 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper3, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper3);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(httpServletResponseWrapper3, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper3);
        setField(httpServletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper2);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@517345fd with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF50() throws Throwable  {
        Inter13 inter13 = new Inter13();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper3 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper4 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper4, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(servletResponseWrapper3, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper4);
        setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper3);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter13.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@6faf3760 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter13.f(Inter13.java:49)
            org.example.inter.Inter13.f(Inter13.java:47) */
        Class inter13Clazz = Class.forName("org.example.inter.Inter13");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter13Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 1;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter13, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter13.getDescription
    
    
    @Test
    public void testGetDescription1() {
        Inter13 inter13 = new Inter13();
        
        String actual = inter13.getDescription();
        
        String expected = "recursive case";
        assertEquals(expected, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter13.getVulnerabilityCount
    
    
    @Test
    public void testGetVulnerabilityCount1() {
        Inter13 inter13 = new Inter13();
        
        int actual = inter13.getVulnerabilityCount();
        
        assertEquals(1, actual);
    }
    
    ///endregion
    
    ///region Util methods
    
    private static Object createInstance(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return Class.forName("sun.misc.Unsafe").getDeclaredMethod("allocateInstance", Class.class)
            .invoke(getUnsafeInstance(), clazz);
    }
    
        private static void setField(Object object, String fieldClassName, String fieldName, Object fieldValue) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Class<?> clazz = Class.forName(fieldClassName);
        java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
    
        java.lang.reflect.Field modifiersField;
        
                java.lang.reflect.Method methodForGetDeclaredFields25829492201600 = java.lang.Class.class.getDeclaredMethod("getDeclaredFields0", boolean.class);
                methodForGetDeclaredFields25829492201600.setAccessible(true);
                java.lang.reflect.Field[] allFieldsFromFieldClass25829492210100 = (java.lang.reflect.Field[]) methodForGetDeclaredFields25829492201600.invoke(java.lang.reflect.Field.class, false);
                modifiersField = java.util.Arrays.stream(allFieldsFromFieldClass25829492210100).filter(field1 -> field1.getName().equals("modifiers")).findFirst().get();
    
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~java.lang.reflect.Modifier.FINAL);
    
        field.setAccessible(true);
        field.set(object, fieldValue);
    }
    
    private static Object getUnsafeInstance() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        java.lang.reflect.Field f = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return f.get(null);
    }
    ///endregion
}

