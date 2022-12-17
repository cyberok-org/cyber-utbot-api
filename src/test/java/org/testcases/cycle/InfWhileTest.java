package org.testcases.cycle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public final class InfWhileTest {
    ///region Test suites for executable org.testcases.cycle.InfWhile.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int, int)
    
    /**
    @utbot.classUnderTest {@link InfWhile}
 * @utbot.methodUnderTest {@link org.testcases.cycle.InfWhile#fun(int,int)}
 * @utbot.returnsFrom {@code return false;}
 *  */
    @Test
    @DisplayName("fun: -> return false")
    public void testFun_ReturnFalse() {
        InfWhile infWhile = new InfWhile();
        
        boolean actual = infWhile.fun(-255, -252);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///region OTHER: TIMEOUTS for method fun(int, int)
    
    @Test
    @org.junit.jupiter.api.Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testFun1() {
        InfWhile infWhile = new InfWhile();
        
        /* This execution may take longer than the 1000 ms timeout
         and therefore fail due to exceeding the timeout. */
        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> infWhile.fun(0, 0));
    }
    ///endregion
    
    ///endregion
}

