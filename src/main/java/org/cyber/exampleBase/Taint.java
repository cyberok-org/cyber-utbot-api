package org.cyber.exampleBase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Objects;

public class Taint {
    boolean check1(String s) {
        if (Objects.equals(s, "aaa")) {
            return true;
        }
        return false;
    }

    boolean example(String s) {
        if (Objects.equals(s, "malicious data")) {
            System.out.println("here1");
            return true;
        }
        System.out.println("here2");
        return false;
    }

    boolean check2(String s, String s2) {
        if (Objects.equals(s, "bbb")) {
            return true;
        }
        return false;
    }

    boolean filesys(Path path, FileAttribute<?>... attrs) throws IOException {
        if (path.toString().contains("..")) {
            return true;
        }
        return false;
    }

    boolean sepclasses(String s1, String s2) { // TODO(проверить, что будет если дать тут условие на первый арг тоже)
        if (s2.contains("aaa")) {
            System.out.println("true here");
            return true;
        }
        System.out.println("false here");
        return false;
    }

    boolean sepclasses1(String s1, String s2, String s3) { // TODO(проверить, что будет если дать тут условие на первый арг тоже)
        if (s2.contains("bb")) {
            System.out.println("true here");
            return true;
        }
        System.out.println("false here");
        return false;
    }

    boolean command_injection(String command, String[] envp, File dir) {
        if (command.contains("aaa")) {
            return true;
        }
        return false;
    }

    boolean pathtraver(String s) {
        if (s.contains("aaa")) return true;
        return false;
    }
}
