package com.mj.serial.service;

import com.mj.serial.serialexception.*;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 * Created by tang on 2017/11/5.
 */
public class SerialTool {

    //私有化SerialTool类构造方法，不允许其他类生成SerialTool对象
    public SerialTool() {
    }

    /**
     * 查询可以用的端口
     *
     * @return 可用端口列表
     */
    public ArrayList<String> findProt() {
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<String>();
        //将串口名添加到List中 并返回List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }

    /**
     * 打开串口
     *
     * @param portName 端口名称
     * @param baudrate 波特率
     * @return 串口对象
     * @throws SerialPortParameterFailure 设置串口参数失败
     * @throws NotASerialPort             端口设备不是串口类型
     * @thorws NoSerialPort 没有该端口对应的串口设备
     * @thorws PortInUse 串口已被占用
     */
    public SerialPort openPort(String portName, int baudrate) throws SerialPortParameterFailure, NotASerialPort, NOSerialPort, PortInUse,Exception {
        try {
            //通过串口名称识别串口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            //打开端口，并给端口名称和链接时间
            CommPort commport = portIdentifier.open(portName, 20);
            if (commport instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commport;
                try {
                    //设置波特率
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                } catch (UnsupportedCommOperationException e) {
                    throw new SerialPortParameterFailure();
                }
                return serialPort;
            } else {
                throw new NotASerialPort();//不是串口
            }
        } catch (NoSuchPortException e1) {
            throw new NOSerialPort();
        } catch (PortInUseException e2) {
            throw new PortInUse();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 关闭串口
     *
     * @param serialPort 待关闭的串口
     */
    public static void colsePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

    /**
     * 向串口发送数据
     *
     * @param serialPort 串口对象
     * @param order      待发送对象
     * @throws sendDataToSerialPortFailurs        向串口发送数据失败
     * @throws SerialPortOutPutStreamCloseFailure 关闭串口对象的输出流错误
     */
    public void sendToPort(SerialPort serialPort, byte[] order) throws sendDataToSerialPortFailurs, SerialPortOutPutStreamCloseFailure {
        OutputStream out = null;
        try {
            out = serialPort.getOutputStream();
            out.write(order);
            out.flush();
        } catch (IOException e) {
            throw new sendDataToSerialPortFailurs();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e1) {
                throw new SerialPortOutPutStreamCloseFailure();
            }
        }
    }

    /**
     * 从串口读出数据
     *
     * @param serialPort 串口对象
     * @return 返回对象
     * @throws SerialPortOutPutStreamCloseFailure 关闭对象流异常
     * @Throws readDataFromserialPortFailurs 从串口读出数据失败
     */
    public String readFromPort(SerialPort serialPort) throws readDataFromserialPortFailurs, SerialPortOutPutStreamCloseFailure, Exception {
        InputStream is = null;
        byte[] bytes = null;
        try {
            is = serialPort.getInputStream();
            bytes = new byte[1024];
            int start = 0;
            while (is.available() > 0) {
                byte[] temp = new byte[2];
                int length = is.read(temp);

                System.arraycopy(temp, 0, bytes, start, length);
                start += length;
            }
            return new String(bytes, "UTF-8");
        } catch (IOException e) {
            throw new readDataFromserialPortFailurs();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException e1) {
                throw new SerialPortOutPutStreamCloseFailure();
            }
        }
    }

    /**
     * 添加监听器
     *
     * @param serialPort 串口对象
     * @param listEner   串口监听器
     * @throws TooManyLissteners 监听类对象过多
     */
    public void addListener(SerialPort serialPort, SerialPortEventListener listEner) throws TooManyLissteners {
        try {
            //给串口添加监听器
            serialPort.addEventListener(listEner);
            //设置当有时间到达唤醒监听接收线程
//            serialPort.notifyOnDataAvailable(true);
//            //设置当通信中断时唤醒中断线程
//            serialPort.notifyOnBreakInterrupt(true);
        } catch (TooManyListenersException e) {
            throw new TooManyLissteners();
        }
    }

}
