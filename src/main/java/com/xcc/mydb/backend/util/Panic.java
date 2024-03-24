package com.xcc.mydb.backend.util;

public class Panic {
    /**
     * 强制停机
     * @param err
     */
    public static void panic(Exception err){
        err.printStackTrace();
        System.exit(1);
    }
}
