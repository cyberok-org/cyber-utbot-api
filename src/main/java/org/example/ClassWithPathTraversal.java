package org.example;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ClassWithPathTraversal {
    public void RAFCreator(HttpServletRequest req) throws IOException {
        String s1 = req.getParameter("name");
        RandomAccessFile f = new RandomAccessFile(s1, "rw");
    }
}
