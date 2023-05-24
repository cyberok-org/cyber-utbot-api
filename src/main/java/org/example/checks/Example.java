package org.example.checks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Example {
    //    public boolean example(HttpServletRequest request) {
//        String param = "";
//        request.getHeader("benchmarkTEST00133");
//        String s = "BenchmarkTest00133";
//        if (request.getHeader(s) != null) {
//            param = request.getHeader(s);
//        }
//        if (param.equals("test")) {
////            param = request.getHeader(s);
////            if (param.equals("test2")) {
//                return true;
////            }
//        }
//        return false;
//    }
    public Example() {
        int s = 1;
        if (new Random().nextBoolean()) {
            s = 2;
        } else {
            s = 4;
            throw new IllegalArgumentException("ff");
        }
    }

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String s = req.getParameter("name");
//        FileInputStream fis = new FileInputStream(s);
////        RandomAccessFile raf = new RandomAccessFile(s, "r");
//    }

    private void internal(String s, int x) {
    }
//
    public boolean example(String s, int x) {
        if (s.length() > 3 && x > 100) {
            internal(s, x);
            return true;
        }
        return false;
    }

//    public boolean example(String s, int a, int b) {
//        if ((s + "/").equals("qwerty/")) {
//            if (a == 123456789 + b) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public boolean example(int a, int b) {
//        if (a - 2500 == b + 10000) {
//            return true;
//        }
//        return false;
//    }

//    public boolean example(String s) {
//        if (s.matches("\\w*(\\.\\.\\/){1,10}etc/passwd")) {
//            return true;
//        }
//        return false;
//    }

//    public boolean example(String s) {
//        if (s.subSequence(10, s.length()).equals("/../etc/passwd")) {
//            return true;
//        }
//        return false;
//    }

//    public boolean example(HttpServletRequest request) {
//        Cookie[] cookie = request.getCookies();
//        if (cookie != null) {
//            Cookie cook = cookie[5];
//            if (cook.getName().equals("cook")) {
//                if (cook.getValue().equals("value")) {
//                    return true;
//                }
//            }
//        }
//        request.getParameter("name");
//        return false;
//    }

//    public boolean example(HttpServletRequest request) {
//        javax.servlet.http.Cookie[] theCookies = request.getCookies();
//        for (javax.servlet.http.Cookie theCookie : theCookies) {
//            if (theCookie.getName().equals("BenchmarkTest00002")) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public boolean example(String[] strings) {
//        for (String str: strings) {
//            if (str.equals("ex")) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public boolean example(HttpServletRequest request) {
//        for (Enumeration<?> values = request.getHeaders("name"); values.hasMoreElements();) {
//            if (values.nextElement().equals("value")) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public boolean example(HttpServletRequest request, String s) {
//        if (request.getHeader("header").equals("header")) {
//            if (request.getHeader(s).equals("s")) {
//                for (Enumeration<?> keys = request.getHeaderNames(); keys.hasMoreElements();) {
//                    if (keys.nextElement().equals("key")) {
////                        if (keys.hasMoreElements() && keys.nextElement().equals("key2")) {
//                            return true;
////                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }

//    public boolean example(HttpServletRequest request) {
//        Enumeration<?> keys = request.getHeaderNames();
//        if (keys.hasMoreElements() && keys.nextElement().equals("key")) {
//            if (keys.hasMoreElements() && keys.nextElement().equals("key2")) {
//                if (keys.hasMoreElements() && keys.nextElement().equals("key3")) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

//    public boolean example(String[] l) {
//        if (l[0].equals("key")) {
//            return true;
//        }
//        return false;
//    }

//    public boolean example(String s) {
//        return File.separator.equals(s);
//    }

//    public boolean example(String s, String s2, String s3, String s4) {
//        if ((s + s2).equals(s3 + "." + s4)) {
//            return true;
//        }
//        return false;
//    }

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
