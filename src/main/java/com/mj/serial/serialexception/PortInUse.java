package com.mj.serial.serialexception;

/**
 * Created by tang on 2017/11/6.
 */
public class PortInUse extends Exception {
    private final static long serialVersionUID = 1L;

    public PortInUse() {
    }

    //串口被占用
    @Override
    public String toString() {
        return "PortInUse";
    }
}
