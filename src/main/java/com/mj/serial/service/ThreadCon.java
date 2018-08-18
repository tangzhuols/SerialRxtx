package com.mj.serial.service;

import com.mj.serial.serialexception.*;

/**
 * Created by tang on 2017/11/7.
 */
public class ThreadCon extends Thread {
    @Override
    public void run() {
        while (true) {

            System.out.println("ServerCon.commPort =" + ServerCon.commPort);
            if (ServerCon.commPort == null) {

                try {
                    ServerCon application = new ServerCon();
                    application.CommContent();
                    System.out.println("ServerCon.commPort != null ==>" + ServerCon.commPort);
                } catch (TooManyLissteners tooManyLissteners) {
                    tooManyLissteners.printStackTrace();
                } catch (SerialPortParameterFailure serialPortParameterFailure) {
                    serialPortParameterFailure.printStackTrace();
                } catch (NOSerialPort noSerialPort) {
                    noSerialPort.printStackTrace();
                } catch (PortInUse portInUse) {
                    portInUse.printStackTrace();
                } catch (NotASerialPort notASerialPort) {
                    notASerialPort.printStackTrace();
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
