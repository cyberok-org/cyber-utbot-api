package org.testcases.cycle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class InfWhile2Test {
    ///region Test suites for executable org.testcases.cycle.InfWhile2.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int, int, int)
    
    /**
    @utbot.classUnderTest {@link InfWhile2}
 * @utbot.methodUnderTest {@link org.testcases.cycle.InfWhile2#fun(int,int,int)}
 * @utbot.returnsFrom {@code return false;}
 *  */
    @Test
    @DisplayName("fun: -> return false")
    public void testFun_ReturnFalse() {
        InfWhile2 infWhile2 = new InfWhile2();
        
        boolean actual = infWhile2.fun(1, 4, -192);
        
        assertFalse(actual);
    }
    
    /**
    @utbot.classUnderTest {@link InfWhile2}
 * @utbot.methodUnderTest {@link org.testcases.cycle.InfWhile2#fun(int,int,int)}
 * @utbot.iterates iterate the loop {@code while(x == y)} once
 * @utbot.returnsFrom {@code return false;}
 *  */
    @Test
    @DisplayName("fun: changeX : True -> return false")
    public void testFun_ChangeX() {
        InfWhile2 infWhile2 = new InfWhile2();
        
        boolean actual = infWhile2.fun(-255, -255, -255);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

