package com.mj.serial.serialexception;

/**
 * Created by tang on 2017/11/6.
 */
public class sendDataToSerialPortFailurs extends Exception {
    private static final long serialVersionUID = 1L;
    public sendDataToSerialPortFailurs(){}
//向串口发送数据失败
    @Override
    public String toString() {
        return "sendDataToSerialPortFailurs!";
    }
}
