package org.testcases.temp;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TempTest {
    ///region Test suites for executable org.testcases.temp.Temp.main
    
    ///region
    
    @Test
    public void testMain1() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        (when(RandomMock.nextInt())).thenReturn(-9);
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            
            Temp.main(null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testMain2() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        (when(RandomMock.nextInt())).thenReturn(-10);
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            
            Temp.main(null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testMain3() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            
            /* This test fails because method [org.testcases.temp.Temp.main] produces [java.lang.IllegalStateException: Could not invoke public int java.util.Random.nextInt() on java.util.Random@73188f83 with arguments []]
                org.mockito.internal.util.reflection.InstrumentationMemberAccessor.invoke(InstrumentationMemberAccessor.java:252)
                org.mockito.internal.util.reflection.ModuleMemberAccessor.invoke(ModuleMemberAccessor.java:55)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice.tryInvoke(MockMethodAdvice.java:333)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice.access$500(MockMethodAdvice.java:60)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice$SerializableRealMethodCall.invoke(MockMethodAdvice.java:292)
                org.mockito.internal.invocation.InterceptedInvocation.callRealMethod(InterceptedInvocation.java:142)
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.mockito.internal.handler.MockHandlerImpl.handle(MockHandlerImpl.java:110)
                org.mockito.internal.handler.NullResultGuardian.handle(NullResultGuardian.java:29)
                org.mockito.internal.handler.InvocationNotifierHandler.handle(InvocationNotifierHandler.java:34)
                org.mockito.internal.creation.bytebuddy.MockMethodInterceptor.doIntercept(MockMethodInterceptor.java:82)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice.handle(MockMethodAdvice.java:151)
                java.base/java.util.Random.nextInt(Random.java:329)
                org.testcases.temp.Temp.main(Temp.java:27) */
            Temp.main(null);
        } finally {
            mockedConstruction.close();
        }
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.temp.Temp.foo
    
    ///region
    
    @Test
    public void testFoo1() {
        int actual = Temp.foo(-255, -255);
        
        assertEquals(65025, actual);
    }
    
    @Test
    public void testFoo2() {
        int actual = Temp.foo(255, 256);
        
        assertEquals(510, actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.temp.Temp.bar
    
    ///region
    
    @Test
    public void testBar1() {
        int actual = Temp.bar();
        
        assertEquals(-10, actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.temp.Temp.vulnerable
    
    ///region
    
    @Test
    public void testVulnerable1() {
        Temp.vulnerable(1);
    }
    ///endregion
    
    ///endregion
}

