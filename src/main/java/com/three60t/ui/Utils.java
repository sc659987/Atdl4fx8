package com.three60t.ui;

public class Utils {

    public static final boolean isEmpty(String s) {
        return s != null && !s.isEmpty() ? false : true;
    }

    public static final boolean isNonEmpty(String s) {
        return s != null && !s.isEmpty() ? true : false;
    }

    // todo put constValue reflection call here

}
