package com.mj.serial.serialexception;

/**
 * Created by tang on 2017/11/6.
 */
public class SerialPortOutPutStreamCloseFailure extends Exception {
    private static final long serialVersionUID = 1L;

    public SerialPortOutPutStreamCloseFailure() {
    }

    //关闭串口对象的输出流错误
    @Override
    public String toString() {
        return "SerialPortOutPutStreamCloseFailure!";
    }
}
