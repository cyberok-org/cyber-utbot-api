package org.cyber.exampleBase;

import java.util.Arrays;

public class DifferentConditions {
    boolean fun0(String s) {
        if (s == null || s.length() < 10) return false;     // fail without that???
        return s.subSequence(0, 7).equals(s.subSequence(3, 10)) && s.charAt(0) != s.charAt(5) && s.charAt(6) != s.charAt(8);
    }

    boolean fun1(String s) {
        return Arrays.asList("a", "b", "c", "d", "e").contains(s);
    }

    boolean fun2(String s) {
        return "zzz".compareTo(s) > 0 && "zzx".compareTo(s) < 0;
    }

    boolean fun3(String s) {
        return s.length() > 30;
    }

    boolean fun4(String s) {
        return s.charAt(25) == '!';
    }

    boolean fun5(String s) {
        return s.contains("~");
    }

    boolean fun6(String s) {
        return s.startsWith("---a") && s.endsWith("a---");
    }

    boolean fun7(String s) {
        return s.matches("FILE*");
    }

    boolean fun8(String s) {
        return s.matches("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
    }

    boolean fun9(String s) {
        return s.matches("(\\.\\.\\/){1,10}FILE");
    }

//    boolean fun10(String s) {   // not work
//        return s.matches("(\\.\\.\\/)+FILE");
//    }

//    boolean fun10(String s) { // not work
//        char[] array = {'a', 'r', 'r', 'a', 'y'};
//        return Arrays.equals(array, s.toCharArray());
//    }

//    boolean fun10(String s) {  // does not end
//        return s.toLowerCase().equals(s.toUpperCase());
//    }

//    boolean fun10(String s) {  // work wrong
//        return s.toLowerCase().equals("qwerty1");
//    }

//    boolean fun10(String s) {  // not work
//        return s.split(" ").length > 1;
//    }

    boolean fun10(String s) {   // not work
        if (s.matches("(\\.\\.\\/){1,10}FILE")) {
            if (s.length() == 22) {
                return true;
            }
        }
        return false;
    }
}
