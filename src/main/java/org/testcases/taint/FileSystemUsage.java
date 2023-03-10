package org.testcases.taint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemUsage {

    public static void foo(int i) {
        try {
            Path p = Path.of(source(i));
            if (i > 0) {
                Files.createFile(p);
            } else {
                Files.createFile(Path.of(source(-10)));
            }
        } catch (IOException e) { System.out.println(e.getMessage()); }
    }

    public void bar(int i) throws IOException {
        Path p = Path.of(source(i));

    }

    private static String source(int i) {
        if (i > 24) {
            return "dangerous"; // dangerous
        } else {
            return "ok"; // ok
        }
    }

    public static void main(String[] args) {
        foo(50);
    }
}
