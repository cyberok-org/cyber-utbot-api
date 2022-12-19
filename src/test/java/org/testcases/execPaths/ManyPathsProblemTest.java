package org.testcases.execPaths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ManyPathsProblemTest {
    ///region Test suites for executable org.testcases.execPaths.ManyPathsProblem.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int, int)
    
    /**
    @utbot.classUnderTest {@link ManyPathsProblem}
 * @utbot.methodUnderTest {@link org.testcases.execPaths.ManyPathsProblem#fun(int,int)}
 * @utbot.executesCondition {@code (y > 0): False}
 * @utbot.executesCondition {@code (x % 2 == 0): False}
 * @utbot.executesCondition {@code (x % 3 == 0): False}
 * @utbot.executesCondition {@code (x % 5 == 0): False}
 * @utbot.executesCondition {@code (x % 7 == 0): True}
 * @utbot.executesCondition {@code (x % 11 == 0): True}
 * @utbot.executesCondition {@code (x % 13 == 0): False}
 * @utbot.executesCondition {@code (x % 17 == 0): False}
 * @utbot.activatesSwitch {@code case 2}
 * @utbot.returnsFrom {@code return res * pr;}
 *  */
    @Test
    @DisplayName("fun: switch(sum) case: 2 -> return res * pr")
    public void testFun_SwitchSumCase2() throws InterruptedException  {
        ManyPathsProblem manyPathsProblem = new ManyPathsProblem();
        
        int actual = manyPathsProblem.fun(77, 0);
        
        assertEquals(3, actual);
    }
    
    /**
    @utbot.classUnderTest {@link ManyPathsProblem}
 * @utbot.methodUnderTest {@link org.testcases.execPaths.ManyPathsProblem#fun(int,int)}
 * @utbot.executesCondition {@code (y > 0): False}
 * @utbot.executesCondition {@code (x % 2 == 0): False}
 * @utbot.executesCondition {@code (x % 3 == 0): False}
 * @utbot.executesCondition {@code (x % 5 == 0): False}
 * @utbot.executesCondition {@code (x % 7 == 0): True}
 * @utbot.executesCondition {@code (x % 11 == 0): True}
 * @utbot.executesCondition {@code (x % 13 == 0): False}
 * @utbot.executesCondition {@code (x % 17 == 0): True}
 * @utbot.activatesSwitch {@code case 3}
 * @utbot.returnsFrom {@code return res * pr;}
 *  */
    @Test
    @DisplayName("fun: switch(sum) case: 3 -> return res * pr")
    public void testFun_SwitchSumCase3() throws InterruptedException  {
        ManyPathsProblem manyPathsProblem = new ManyPathsProblem();
        
        int actual = manyPathsProblem.fun(2040601409, 0);
        
        assertEquals(4, actual);
    }
    
    /**
    @utbot.classUnderTest {@link ManyPathsProblem}
 * @utbot.methodUnderTest {@link org.testcases.execPaths.ManyPathsProblem#fun(int,int)}
 * @utbot.executesCondition {@code (y > 0): True}
 * @utbot.executesCondition {@code (x % 2 == 0): False}
 * @utbot.executesCondition {@code (x % 3 == 0): False}
 * @utbot.executesCondition {@code (x % 5 == 0): False}
 * @utbot.executesCondition {@code (x % 7 == 0): False}
 * @utbot.executesCondition {@code (x % 11 == 0): False}
 * @utbot.executesCondition {@code (x % 13 == 0): True}
 * @utbot.executesCondition {@code (x % 17 == 0): False}
 * @utbot.activatesSwitch {@code case 1}
 * @utbot.returnsFrom {@code return res * pr;}
 *  */
    @Test
    @DisplayName("fun: switch(sum) case: 1 -> return res * pr")
    public void testFun_SwitchSumCase1() throws InterruptedException  {
        ManyPathsProblem manyPathsProblem = new ManyPathsProblem();
        
        int actual = manyPathsProblem.fun(13, 1);
        
        assertEquals(-2, actual);
    }
    
    /**
    @utbot.classUnderTest {@link ManyPathsProblem}
 * @utbot.methodUnderTest {@link org.testcases.execPaths.ManyPathsProblem#fun(int,int)}
 * @utbot.executesCondition {@code (y > 0): True}
 * @utbot.executesCondition {@code (x % 2 == 0): False}
 * @utbot.executesCondition {@code (x % 3 == 0): False}
 * @utbot.executesCondition {@code (x % 5 == 0): False}
 * @utbot.executesCondition {@code (x % 7 == 0): False}
 * @utbot.executesCondition {@code (x % 11 == 0): False}
 * @utbot.executesCondition {@code (x % 13 == 0): False}
 * @utbot.executesCondition {@code (x % 17 == 0): False}
 * @utbot.activatesSwitch {@code case 0}
 * @utbot.returnsFrom {@code return res * pr;}
 *  */
    @Test
    @DisplayName("fun: switch(sum) case: 0 -> return res * pr")
    public void testFun_SwitchSumCase0() throws InterruptedException  {
        ManyPathsProblem manyPathsProblem = new ManyPathsProblem();
        
        int actual = manyPathsProblem.fun(1, 1);
        
        assertEquals(-1, actual);
    }
    
    /**
    @utbot.classUnderTest {@link ManyPathsProblem}
 * @utbot.methodUnderTest {@link org.testcases.execPaths.ManyPathsProblem#fun(int,int)}
 * @utbot.executesCondition {@code (y > 0): True}
 * @utbot.executesCondition {@code (x % 2 == 0): True}
 * @utbot.executesCondition {@code (x % 3 == 0): True}
 * @utbot.executesCondition {@code (x % 5 == 0): False}
 * @utbot.executesCondition {@code (x % 7 == 0): True}
 * @utbot.executesCondition {@code (x % 11 == 0): False}
 * @utbot.executesCondition {@code (x % 13 == 0): True}
 * @utbot.executesCondition {@code (x % 17 == 0): False}
 * @utbot.returnsFrom {@code return res * pr;}
 *  */
    @Test
    @DisplayName("fun: y > 0 : True -> return res * pr")
    public void testFun_XRemainderOf17NotEqualsZero() throws InterruptedException  {
        ManyPathsProblem manyPathsProblem = new ManyPathsProblem();
        
        int actual = manyPathsProblem.fun(286261248, 1);
        
        assertEquals(-5, actual);
    }
    
    /**
    @utbot.classUnderTest {@link ManyPathsProblem}
 * @utbot.methodUnderTest {@link org.testcases.execPaths.ManyPathsProblem#fun(int,int)}
 * @utbot.executesCondition {@code (y > 0): True}
 * @utbot.executesCondition {@code (x % 2 == 0): True}
 * @utbot.executesCondition {@code (x % 3 == 0): True}
 * @utbot.executesCondition {@code (x % 5 == 0): False}
 * @utbot.executesCondition {@code (x % 7 == 0): True}
 * @utbot.executesCondition {@code (x % 11 == 0): False}
 * @utbot.executesCondition {@code (x % 13 == 0): True}
 * @utbot.executesCondition {@code (x % 17 == 0): True}
 * @utbot.activatesSwitch {@code case 5}
 * @utbot.returnsFrom {@code return res * pr;}
 *  */
    @Test
    @DisplayName("fun: switch(sum) case: 5 -> return res * pr")
    public void testFun_SwitchSumCase5() throws InterruptedException  {
        ManyPathsProblem manyPathsProblem = new ManyPathsProblem();
        
        int actual = manyPathsProblem.fun(1048866, 1);
        
        assertEquals(-6, actual);
    }
    
    /**
    @utbot.classUnderTest {@link ManyPathsProblem}
 * @utbot.methodUnderTest {@link org.testcases.execPaths.ManyPathsProblem#fun(int,int)}
 * @utbot.executesCondition {@code (y > 0): True}
 * @utbot.executesCondition {@code (x % 2 == 0): True}
 * @utbot.executesCondition {@code (x % 3 == 0): True}
 * @utbot.executesCondition {@code (x % 5 == 0): True}
 * @utbot.executesCondition {@code (x % 7 == 0): False}
 * @utbot.executesCondition {@code (x % 11 == 0): False}
 * @utbot.executesCondition {@code (x % 13 == 0): True}
 * @utbot.executesCondition {@code (x % 17 == 0): False}
 * @utbot.returnsFrom {@code return res * pr;}
 *  */
    @Test
    @DisplayName("fun: y > 0 : True -> return res * pr")
    public void testFun_XRemainderOf17NotEqualsZero_1() throws InterruptedException  {
        ManyPathsProblem manyPathsProblem = new ManyPathsProblem();
        
        int actual = manyPathsProblem.fun(863642910, 1);
        
        assertEquals(-5, actual);
    }
    
    /**
    @utbot.classUnderTest {@link ManyPathsProblem}
 * @utbot.methodUnderTest {@link org.testcases.execPaths.ManyPathsProblem#fun(int,int)}
 * @utbot.executesCondition {@code (y > 0): True}
 * @utbot.executesCondition {@code (x % 2 == 0): True}
 * @utbot.executesCondition {@code (x % 3 == 0): True}
 * @utbot.executesCondition {@code (x % 5 == 0): True}
 * @utbot.executesCondition {@code (x % 7 == 0): True}
 * @utbot.executesCondition {@code (x % 11 == 0): False}
 * @utbot.executesCondition {@code (x % 13 == 0): True}
 * @utbot.executesCondition {@code (x % 17 == 0): True}
 * @utbot.activatesSwitch {@code case 6}
 * @utbot.returnsFrom {@code return res * pr;}
 *  */
    @Test
    @DisplayName("fun: switch(sum) case: 6 -> return res * pr")
    public void testFun_SwitchSumCase6() throws InterruptedException  {
        ManyPathsProblem manyPathsProblem = new ManyPathsProblem();
        
        int actual = manyPathsProblem.fun(487629870, 1);
        
        assertEquals(-7, actual);
    }
    
    /**
    @utbot.classUnderTest {@link ManyPathsProblem}
 * @utbot.methodUnderTest {@link org.testcases.execPaths.ManyPathsProblem#fun(int,int)}
 * @utbot.executesCondition {@code (y > 0): True}
 * @utbot.executesCondition {@code (x % 2 == 0): True}
 * @utbot.executesCondition {@code (x % 3 == 0): True}
 * @utbot.executesCondition {@code (x % 5 == 0): True}
 * @utbot.executesCondition {@code (x % 7 == 0): True}
 * @utbot.executesCondition {@code (x % 11 == 0): True}
 * @utbot.executesCondition {@code (x % 13 == 0): True}
 * @utbot.executesCondition {@code (x % 17 == 0): True}
 * @utbot.activatesSwitch {@code case 7}
 * @utbot.returnsFrom {@code return res * pr;}
 *  */
    @Test
    @DisplayName("fun: switch(sum) case: 7 -> return res * pr")
    public void testFun_SwitchSumCase7() throws InterruptedException  {
        ManyPathsProblem manyPathsProblem = new ManyPathsProblem();
        
        int actual = manyPathsProblem.fun(0, 1);
        
        assertEquals(-8, actual);
    }
    ///endregion
    
    ///endregion
}

