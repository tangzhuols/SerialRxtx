package com.mj.serial.serialexception;

/**
 * Created by tang on 2017/11/6.
 */
public class NotASerialPort extends Exception {
    private static final long serialVersionUID = 1L;

    public NotASerialPort() {
    }

    //端口指向设备不是串口类型
    @Override
    public String toString() {
        return "NotASerialPort!";
    }
}
