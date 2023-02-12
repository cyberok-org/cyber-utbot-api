package org.example;


public class Loop {
    private boolean fun4(String s) {
        String a = s;
        return true;
    }

//    public int whileLoop(int k) {
//        if (k >= 0) {
//            fun2(k);
//        }
//        int i = 0;
//        int sum = 0;
//
//        while (i < k) {
//            sum += i;
//            i += 1;
//        }
//
//        return sum;
//    }

    public boolean fun(String s) {
        return fun4(s);

//        return Objects.equals(s, "1");
//        if (Objects.equals(s, "1")) {
//            return 1;
//        }
//        if (fun3(s)) {
//            return 1;
//        }
//        return 2;
    }
}
