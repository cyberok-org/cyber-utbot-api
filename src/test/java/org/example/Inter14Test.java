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

public final class Inter14Test {
    ///region Test suites for executable org.example.inter.Inter14.f
    
    
    @Test
    public void testF1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException  {
        Inter14 inter14 = new Inter14();
        
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Class servletResponseType = Class.forName("javax.servlet.ServletResponse");
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseType);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = 0;
        fMethodArguments[2] = ((Object) null);
        fMethod.invoke(inter14, fMethodArguments);
    }
    
    @Test
    public void testF2() throws Throwable  {
        Inter14 inter14 = new Inter14();
        
        /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.NullPointerException]
            org.example.inter.Inter14.f(Inter14.java:51) */
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Class servletResponseType = Class.forName("javax.servlet.ServletResponse");
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseType);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = -255;
        fMethodArguments[2] = ((Object) null);
        try {
            fMethod.invoke(inter14, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF3() throws Throwable  {
        Inter14 inter14 = new Inter14();
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@517c6b6e with arguments []]
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
            org.example.inter.Inter14.f(Inter14.java:51) */
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = -255;
        fMethodArguments[2] = noBodyResponseMock;
        try {
            fMethod.invoke(inter14, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF4() throws Throwable  {
        Inter14 inter14 = new Inter14();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        
        /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@4caa23e3 with arguments []]
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
            org.example.inter.Inter14.f(Inter14.java:51) */
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = -256;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter14, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF5() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter14 inter14 = new Inter14();
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@247a3e96 with arguments []]
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
                org.example.inter.Inter14.f(Inter14.java:51) */
            Class inter14Clazz = Class.forName("org.example.inter.Inter14");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 1;
            fMethodArguments[2] = noBodyResponseMock;
            try {
                fMethod.invoke(inter14, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF6() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter14 inter14 = new Inter14();
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
            
            /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@4bf4d9bb with arguments []]
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
                org.example.inter.Inter14.f(Inter14.java:51) */
            Class inter14Clazz = Class.forName("org.example.inter.Inter14");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = -255;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter14, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF7() throws Throwable  {
        Inter14 inter14 = new Inter14();
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
        
        /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@3b315c5d with arguments []]
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
            org.example.inter.Inter14.f(Inter14.java:51) */
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = -255;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter14, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF8() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter14 inter14 = new Inter14();
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
            
            /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@3743d02b with arguments []]
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
                org.example.inter.Inter14.f(Inter14.java:51) */
            Class inter14Clazz = Class.forName("org.example.inter.Inter14");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 32;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter14, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF9() throws Throwable  {
        Inter14 inter14 = new Inter14();
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
        
        /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@4caa7d3 with arguments []]
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
            org.example.inter.Inter14.f(Inter14.java:51) */
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = -255;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter14, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF10() throws Throwable  {
        Inter14 inter14 = new Inter14();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
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
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        
        /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@743d597 with arguments []]
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
            org.example.inter.Inter14.f(Inter14.java:51) */
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = -128;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter14, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF11() throws Throwable  {
        Inter14 inter14 = new Inter14();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
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
        setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@42371169 with arguments []]
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
            org.example.inter.Inter14.f(Inter14.java:51) */
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = -254;
        fMethodArguments[2] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter14, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF12() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter14 inter14 = new Inter14();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
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
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@18c61adb with arguments []]
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
                org.example.inter.Inter14.f(Inter14.java:51) */
            Class inter14Clazz = Class.forName("org.example.inter.Inter14");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 4;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter14, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF13() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter14 inter14 = new Inter14();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
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
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@6e2176ad with arguments []]
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
                org.example.inter.Inter14.f(Inter14.java:51) */
            Class inter14Clazz = Class.forName("org.example.inter.Inter14");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 32;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter14, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF14() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter14 inter14 = new Inter14();
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
            
            /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@52393c32 with arguments []]
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
                org.example.inter.Inter14.f(Inter14.java:51) */
            Class inter14Clazz = Class.forName("org.example.inter.Inter14");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 1;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter14, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF15() throws Throwable  {
        Inter14 inter14 = new Inter14();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
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
        setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        
        /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@2fa65ead with arguments []]
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
            org.example.inter.Inter14.f(Inter14.java:51) */
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = -254;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter14, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF16() throws Throwable  {
        Inter14 inter14 = new Inter14();
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        HttpServletResponseWrapper httpServletResponseWrapper2 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
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
        setField(httpServletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
        setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper2);
        setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@4d852da4 with arguments []]
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
            org.example.inter.Inter14.f(Inter14.java:51) */
        Class inter14Clazz = Class.forName("org.example.inter.Inter14");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[3];
        fMethodArguments[0] = ((Object) null);
        fMethodArguments[1] = -224;
        fMethodArguments[2] = servletResponseWrapper;
        try {
            fMethod.invoke(inter14, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF17() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter14 inter14 = new Inter14();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper2 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
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
            setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper3);
            setField(servletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper2);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@35e65cf6 with arguments []]
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
                org.example.inter.Inter14.f(Inter14.java:51) */
            Class inter14Clazz = Class.forName("org.example.inter.Inter14");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 8;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter14, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF18() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter14 inter14 = new Inter14();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper1 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper2 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            HttpServletResponseWrapper httpServletResponseWrapper3 = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
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
            setField(httpServletResponseWrapper3, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            setField(httpServletResponseWrapper2, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper3);
            setField(httpServletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper2);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper1);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", httpServletResponseWrapper);
            
            /* This test fails because method [org.example.inter.Inter14.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@2ea456e5 with arguments []]
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
                org.example.inter.Inter14.f(Inter14.java:51) */
            Class inter14Clazz = Class.forName("org.example.inter.Inter14");
            Class stringType = Class.forName("java.lang.String");
            Class intType = int.class;
            Method fMethod = inter14Clazz.getDeclaredMethod("f", stringType, intType, servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[3];
            fMethodArguments[0] = ((Object) null);
            fMethodArguments[1] = 128;
            fMethodArguments[2] = servletResponseWrapper;
            try {
                fMethod.invoke(inter14, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter14.doGet
    
    
    @Test
    public void testDoGet1() throws IOException  {
        Inter14 inter14 = new Inter14();
        
        /* This test fails because method [org.example.inter.Inter14.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter14.doGet(Inter14.java:40) */
        inter14.doGet(null, null);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter14.getDescription
    
    
    @Test
    public void testGetDescription1() {
        Inter14 inter14 = new Inter14();
        
        String actual = inter14.getDescription();
        
        String expected = "interprocedural loop";
        assertEquals(expected, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter14.getVulnerabilityCount
    
    
    @Test
    public void testGetVulnerabilityCount1() {
        Inter14 inter14 = new Inter14();
        
        int actual = inter14.getVulnerabilityCount();
        
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
        
                java.lang.reflect.Method methodForGetDeclaredFields47638441474300 = java.lang.Class.class.getDeclaredMethod("getDeclaredFields0", boolean.class);
                methodForGetDeclaredFields47638441474300.setAccessible(true);
                java.lang.reflect.Field[] allFieldsFromFieldClass47638441483000 = (java.lang.reflect.Field[]) methodForGetDeclaredFields47638441474300.invoke(java.lang.reflect.Field.class, false);
                modifiersField = java.util.Arrays.stream(allFieldsFromFieldClass47638441483000).filter(field1 -> field1.getName().equals("modifiers")).findFirst().get();
    
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

