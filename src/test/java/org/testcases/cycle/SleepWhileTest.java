package org.testcases.cycle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public final class SleepWhileTest {
    ///region Test suites for executable org.testcases.cycle.SleepWhile.fun
    
    ///region SYMBOLIC EXECUTION: TIMEOUTS for method fun(int)
    
    /**
    @utbot.classUnderTest {@link SleepWhile}
 * @utbot.methodUnderTest {@link org.testcases.cycle.SleepWhile#fun(int)}
 * @utbot.executesCondition {@code (x == 5): True}
 * @utbot.invokes {@link java.lang.Thread#sleep(long)}
 * @utbot.returnsFrom {@code return true;}
 * @utbot.throwsException {@link org.utbot.framework.plugin.api.TimeoutException} in: return true;
 *  */
    @Test
    @DisplayName("fun: return true -> ThrowTimeoutException")
    @org.junit.jupiter.api.Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testFun_XEquals5() throws InterruptedException  {
        SleepWhile sleepWhile = new SleepWhile();
        
        /* This execution may take longer than the 1000 ms timeout
         and therefore fail due to exceeding the timeout. */
        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> sleepWhile.fun(5));
    }
    ///endregion
    
    ///endregion
}

