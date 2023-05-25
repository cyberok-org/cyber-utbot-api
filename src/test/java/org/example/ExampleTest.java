package org.example.checks;

import org.junit.jupiter.api.Test;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;

public final class ExampleTest {
    ///region Test suites for executable org.example.checks.Example.example
    
    
    @Test
    public void testExample1() {
        Example example = new Example();
        
        /* This test fails because method [org.example.checks.Example.example] produces [java.lang.NullPointerException]
            org.example.checks.Example.example(Example.java:10) */
        example.example(null);
    }
    
    @Test
    public void testExample2() {
        Example example = new Example();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.checks.Example.example] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.checks.Example.example(Example.java:10) */
        example.example(httpServletRequestMock);
    }
    
    @Test
    public void testExample3() {
        Example example = new Example();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.checks.Example.example] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.checks.Example.example(Example.java:10) */
        example.example(httpServletRequestMock);
    }
    
    @Test
    public void testExample4() {
        Example example = new Example();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.checks.Example.example] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.checks.Example.example(Example.java:10) */
        example.example(httpServletRequestMock);
    }
    
    ///endregion
}

