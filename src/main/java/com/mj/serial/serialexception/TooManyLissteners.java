package com.mj.serial.serialexception;

/**
 * Created by tang on 2017/11/6.
 */
public class TooManyLissteners extends Exception {
    private static final long serialVasionUID = 1L;

    public TooManyLissteners() {
    }

    //监听类对象过多
    @Override
    public String toString() {
        return "TooManyLissteners!";
    }
}
