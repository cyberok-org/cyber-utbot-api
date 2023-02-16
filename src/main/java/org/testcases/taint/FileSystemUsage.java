package org.testcases.taint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemUsage {

    public void foo(int i) throws IOException {
        Path p = Path.of(source(i));
        if (i > 0) {
            Files.createFile(p);
        } else {
            Files.createFile(Path.of(source(-10)));
        }
    }

    public String source(int i) {
        if (i > 24) {
            return "dangerous";
        } else {
            return "ok";
        }
    }
}
