package org.cyber.exploitBase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class CommandTest {
    ///region Test suites for executable org.cyber.exploitBase.Command.commandCheckWindows
    
    
    /**
     * @utbot.classUnderTest {@link org.cyber.exploitBase.Command}
     * @utbot.methodUnderTest {@link org.cyber.exploitBase.Command#commandCheckWindows(java.lang.String[])}
     */
    @Test
    @DisplayName("commandCheckWindows: s = String[4] -> return false")
    public void testCommandCheckWindowsReturnsFalseWithNonEmptyObjectArray() {
        Command command = new Command();
        java.lang.String[] stringArray = {"", "&& ls", "; w\\ho\\am\\i", ""};
        
        boolean actual = command.commandCheckWindows(stringArray);
        
        assertFalse(actual);
    }
    
    ///region FUZZER: SUCCESSFUL EXECUTIONS for method commandCheckWindows(java.lang.String[])
    
    /**
     * @utbot.classUnderTest {@link org.cyber.exploitBase.Command}
     * @utbot.methodUnderTest {@link org.cyber.exploitBase.Command#commandCheckWindows(java.lang.String[])}
     */
    @Test
    @DisplayName("commandCheckWindows: commandCheckWindows: s = String[4] -> return false")
    public void testCommandCheckWindowsReturnsFalseWithNonEmptyObjectArray1() {
        Command command = new Command();
        java.lang.String[] stringArray = {"", "&& ls", "; w\\ho\\am\\i", ""};
        
        boolean actual = command.commandCheckWindows(stringArray);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.cyber.exploitBase.Command.commandCheckUnix
    
    
    /**
     * @utbot.classUnderTest {@link org.cyber.exploitBase.Command}
     * @utbot.methodUnderTest {@link org.cyber.exploitBase.Command#commandCheckUnix(java.lang.String[])}
     */
    @Test
    @DisplayName("commandCheckUnix: s = String[4] -> return false")
    public void testCommandCheckUnixReturnsFalseWithNonEmptyObjectArray() {
        Command command = new Command();
        java.lang.String[] stringArray = {"", "| who$()ami", "\nwho$(echo am)i", ""};
        
        boolean actual = command.commandCheckUnix(stringArray);
        
        assertFalse(actual);
    }
    
    ///region FUZZER: SUCCESSFUL EXECUTIONS for method commandCheckUnix(java.lang.String[])
    
    /**
     * @utbot.classUnderTest {@link org.cyber.exploitBase.Command}
     * @utbot.methodUnderTest {@link org.cyber.exploitBase.Command#commandCheckUnix(java.lang.String[])}
     */
    @Test
    @DisplayName("commandCheckUnix: commandCheckUnix: s = String[4] -> return false")
    public void testCommandCheckUnixReturnsFalseWithNonEmptyObjectArray1() {
        Command command = new Command();
        java.lang.String[] stringArray = {"", "| who$()ami", "\nwho$(echo am)i", ""};
        
        boolean actual = command.commandCheckUnix(stringArray);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

