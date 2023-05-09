package org.example.checks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;

public final class ExampleTest {
    ///region Test suites for executable org.example.checks.Example.example
    
    ///region SYMBOLIC EXECUTION: ERROR SUITE for method example(javax.servlet.http.HttpServletRequest)
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#example(javax.servlet.http.HttpServletRequest)}
 * @utbot.throwsException {@link java.lang.NullPointerException} in: request.getHeader("benchmarkTEST00133");
 *  */
    @Test
    @DisplayName("example: request.getHeader(\"benchmarkTEST00133\") : True -> ThrowNullPointerException")
    public void testExample_ThrowNullPointerException() {
        Example example = new Example();
        
        /* This test fails because method [org.example.checks.Example.example] produces [java.lang.NullPointerException]
            org.example.checks.Example.example(Example.java:10) */
        example.example(null);
    }
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#example(javax.servlet.http.HttpServletRequest)}
 * @utbot.executesCondition {@code (request.getHeader(s) != null): True}
 * @utbot.executesCondition {@code (param.equals("test")): False}
 * @utbot.returnsFrom {@code return false;}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: return false;
 *  */
    @Test
    @DisplayName("example: return false -> ThrowMockitoException")
    public void testExample_ThrowMockitoException() {
        Example example = new Example();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.checks.Example.example] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.checks.Example.example(Example.java:10) */
        example.example(httpServletRequestMock);
    }
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#example(javax.servlet.http.HttpServletRequest)}
 * @utbot.executesCondition {@code (request.getHeader(s) != null): True}
 * @utbot.executesCondition {@code (param.equals("test")): False}
 * @utbot.returnsFrom {@code return false;}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: return false;
 *  */
    @Test
    @DisplayName("example: return false -> ThrowMockitoException")
    public void testExample_ThrowMockitoException_1() {
        Example example = new Example();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.checks.Example.example] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.checks.Example.example(Example.java:10) */
        example.example(httpServletRequestMock);
    }
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#example(javax.servlet.http.HttpServletRequest)}
 * @utbot.executesCondition {@code (request.getHeader(s) != null): True}
 * @utbot.executesCondition {@code (param.equals("test")): True}
 * @utbot.returnsFrom {@code return true;}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: return true;
 *  */
    @Test
    @DisplayName("example: return true -> ThrowMockitoException")
    public void testExample_ParamEquals() {
        Example example = new Example();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        
        /* This test fails because method [org.example.checks.Example.example] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.checks.Example.example(Example.java:10) */
        example.example(httpServletRequestMock);
    }
    ///endregion
    
    ///endregion
}

