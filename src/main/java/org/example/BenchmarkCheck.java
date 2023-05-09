package org.example;

import org.utbot.engine.overrides.collections.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

public class BenchmarkCheck {

    public int example(String s1, String s2) {
        String sss = s1 + s2 + "value1";
        String s3 = "value2";
        if (s1.equals(s3)) return 0;
        else if (s1.equals("../etc/passwd")) return 2;
        System.out.println(s1);
        return 13;
    }

}
