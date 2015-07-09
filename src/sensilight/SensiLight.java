/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensilight;


import jssc.SerialPort;
import jssc.SerialPortException;
import de.androidpit.colorthief.ColorThief;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;


/**
 *
 * @author akrv
 */
public class SensiLight {

    /**
     * @param args the command line arguments
     * @throws java.awt.AWTException
     */
 
    static SerialPort serialPort;
    public static void main(String[] args) throws SerialPortException, AWTException{
    Runtime.getRuntime().addShutdownHook(new Message());
    byte[] buffer;
    SerialInit("COM6");
    while (true){
       
    //start of while loop
    BufferedImage capture = getImage();
    int[] color = ColorThief.getColor(capture,5,true);
    String values = color[0]+","+color[1]+","+color[2]+".";
    buffer = values.getBytes();
    boolean writeBytes = serialPort.writeBytes(buffer);
    }   
}

    //initialize the serialport
    static void SerialInit(String port) {
        serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
//            serialPort.setParams(   SerialPort.BAUDRATE_9600,
//                                    SerialPort.DATABITS_8,
//                                    SerialPort.STOPBITS_1,
//                                    SerialPort.PARITY_NONE);
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
    //Close the connection
    static void SerialClose() {
        try {
            serialPort.closePort();
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    private static BufferedImage getImage() throws AWTException {
    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    BufferedImage capture = new Robot().createScreenCapture(screenRect);
    return capture;
    }
    
       // a class that extends thread that is to be called when program is exiting
   static class Message extends Thread {
   @Override
   public void run() {
       SerialClose();
   System.out.println("Bye.");
   }
   }

}


