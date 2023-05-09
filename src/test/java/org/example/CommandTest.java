package org.cyber.exploitBase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class CommandTest {
    ///region Test suites for executable org.cyber.exploitBase.Command.commandCheckWindows
    
    
    @Test
    public void testCommandCheckWindows1() {
        Command command = new Command();
        
        /* This test fails because method [org.cyber.exploitBase.Command.commandCheckWindows] produces [java.lang.NullPointerException]
            org.cyber.exploitBase.Command.commandCheckWindows(Command.java:9) */
        command.commandCheckWindows(null);
    }
    
    @Test
    public void testCommandCheckWindows2() {
        Command command = new Command();
        String string = " ";
        
        boolean actual = command.commandCheckWindows(string);
        
        assertFalse(actual);
    }
    
    @Test
    public void testCommandCheckWindows3() {
        Command command = new Command();
        String string = "   ";
        
        boolean actual = command.commandCheckWindows(string);
        
        assertFalse(actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.cyber.exploitBase.Command.commandCheckUnix
    
    
    @Test
    public void testCommandCheckUnix1() {
        Command command = new Command();
        
        /* This test fails because method [org.cyber.exploitBase.Command.commandCheckUnix] produces [java.lang.NullPointerException]
            org.cyber.exploitBase.Command.commandCheckUnix(Command.java:6) */
        command.commandCheckUnix(null);
    }
    
    @Test
    public void testCommandCheckUnix2() {
        Command command = new Command();
        String string = " ";
        
        boolean actual = command.commandCheckUnix(string);
        
        assertFalse(actual);
    }
    
    @Test
    public void testCommandCheckUnix3() {
        Command command = new Command();
        String string = "        ";
        
        boolean actual = command.commandCheckUnix(string);
        
        assertFalse(actual);
    }
    
    ///endregion
}

