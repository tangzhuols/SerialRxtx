package com.mj.serial.service;

import com.mj.serial.serialexception.*;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.List;

/**
 * Created by tang on 2017/11/7.
 */
public class ServerCon {
    public static SerialPort commPort = null;
    public static String data = "";
    public static List<String> commList = null;
    SerialTool serialTool = new SerialTool();

    public void CommContent() throws TooManyLissteners, SerialPortParameterFailure, NOSerialPort, PortInUse, NotASerialPort {
        try {
            commList = serialTool.findProt();

            if (!commList.isEmpty() && commList.size() > 0) {
                for (String serial : commList
                        ) {
                    commPort = serialTool.openPort(serial, 9600);
                    System.out.println("commPort = " + commPort);
                }
                if (commPort != null) {
                    serialTool.addListener(commPort, new SerialListener());
                }
            } else {
                commList = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class SerialListener implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent serialPortEvent) {
            try {
                switch (serialPortEvent.getEventType()) {
                    case SerialPortEvent.BI:
                    case SerialPortEvent.OE:/*Overrun error?????*/
                    case SerialPortEvent.FE:/*Framing error?????*/
                    case SerialPortEvent.PE:/*Parity error?????*/
                    case SerialPortEvent.CD:/*Carrier detect?????*/
                    case SerialPortEvent.CTS:/*Clear to send?????*/
                    case SerialPortEvent.DSR:/*Data set ready???????*/
                    case SerialPortEvent.RI:/*Ring indicator?????*/
                    case SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is empty????????*/
                        break;
                    case SerialPortEvent.DATA_AVAILABLE:
                        try {
                            String res = serialTool.readFromPort(commPort);
                            if (res.contains("\n")) {
                                data += res;
//                            String s = HttpRequest.sendGet("http://191.72.240.172:8080/GasAutomationApi/api/ProductionPlan/GetProductionPlans", "data=" + data.replace("\r","").replace("\n", ""));?
                                System.out.println("data = " + data.replace("\r", "").replace("\n", ""));
                                data = "";
                            } else {
                                data += res;
                            }
                        } catch (readDataFromserialPortFailurs readDataFromserialPortFailurs) {
                            if (commPort != null)
                                SerialTool.colsePort(commPort);
                            commPort = null;
                            commList = null;
                            System.out.println("12333333");
                            System.exit(1);
                            return;
                        } catch (SerialPortOutPutStreamCloseFailure serialPortOutPutStreamCloseFailure) {
                            serialPortOutPutStreamCloseFailure.printStackTrace();
                        } catch (Exception e) {
                            if (commPort != null)
                                SerialTool.colsePort(commPort);
                            commPort = null;
                            commList = null;
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
