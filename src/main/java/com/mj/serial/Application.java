package com.mj.serial;

import com.mj.serial.httpconnection.HttpRequest;
import com.mj.serial.serialexception.*;
import com.mj.serial.service.SerialTool;
import com.mj.serial.service.ServerCon;
import com.mj.serial.service.ThreadCon;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by tang on 2017/11/5.
 */
//@SpringBootApplication
//@EnableScheduling
public class Application {

    public static void main(String[] args){
//        SpringApplication.run(Application.class, args);
        ThreadCon threadCon = new ThreadCon();
        threadCon.start();
//        ServerCon application = new ServerCon();
//        application.CommContent();
    }
}
