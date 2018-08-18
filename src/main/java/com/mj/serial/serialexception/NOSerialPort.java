package com.mj.serial.serialexception;

/**
 * Created by tang on 2017/11/6.
 */
public class NOSerialPort extends Exception {
    private static final long serialVersionUID = 1L;

    public NOSerialPort() {
    }

    //没有该端口对应的串口设备
    @Override
    public String toString() {
        return "NOSerialPort";
    }
}
