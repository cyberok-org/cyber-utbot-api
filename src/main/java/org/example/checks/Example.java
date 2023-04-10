package org.example.checks;

import javax.servlet.http.HttpServletRequest;

public class Example {
    public boolean example(HttpServletRequest request) {
        String param = "";
        request.getHeader("benchmarkTEST00133");
        String s = "BenchmarkTest00133";
        if (request.getHeader(s) != null) {
            param = request.getHeader(s);
        }
        if (param.equals("test")) {
//            param = request.getHeader(s);
//            if (param.equals("test2")) {
                return true;
//            }
        }
        return false;
    }

//    public boolean example(HttpServletRequest request) {
//        if (request.getHeader("header").equals("abc")) {
//            return true;
//        }
//        return false;
//    }

//    public boolean example(HttpServletRequest request, String s) {
//        Enumeration<String> headers = request.getHeaders("");
//        if (headers.toString().equals("test")) {
////            if (headers.nextElement().toString().equals("test2")) {
////                return true;
////            }
//            return true;
//        }
//        return false;
//    }

//    public boolean example(String s) {
//        if (s == null) {
//            return true;
//        }
//        Path path = Paths.get(s);
//        if (path.toString().equals("../etc/passwd")) {
//            return true;
//        }
//        return false;
//    }
}
