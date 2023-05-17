package org.cyber.exampleBase;

public class Command {
    //TODO: add sophisticated payloads
        boolean commandCheckUnix(String... s)  {
            return s[0].equals("uname -a");
        }

        boolean commandCheckWindows(String... s)  { return s[0].equals("ver"); }
}
