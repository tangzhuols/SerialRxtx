package com.mj.serial.serialexception;

/**
 * Created by tang on 2017/11/6.
 */
public class SerialPortParameterFailure extends Exception {
    private static final long serialVersionUID = 1L;

    public SerialPortParameterFailure() {
    }

    //设置串口参数失败！打开串口操作未完成
    @Override
    public String toString() {
        return "SerialPortParameterFailure";
    }
}
