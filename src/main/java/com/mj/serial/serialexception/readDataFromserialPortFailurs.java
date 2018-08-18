package com.mj.serial.serialexception;

/**
 * Created by tang on 2017/11/6.
 */
public class readDataFromserialPortFailurs extends Exception {
    private static final long serialVasionUID = 1L;

    public readDataFromserialPortFailurs() {
    }

    //无法从串口读取数据
    @Override
    public String toString() {
        return "readDataFromserialPortFailurs!";
    }
}
