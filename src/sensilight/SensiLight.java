/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensilight;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import jssc.SerialPort;
import jssc.SerialPortException;
import de.androidpit.colorthief.ColorThief;


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
    int[] dataBuffInt;
    int counter = 0;
//    int sumR = 0;
//    int sumG = 0;
//    int sumB = 0;
//    int avgR,avgG,avgB;
    byte[] buffer;
//    int h,w,imageLen;
    //int[] redz,greenz,bluez;
    buffer = new byte[11];
    SerialInit("COM6");
    
    while (true){
       
    //start of while loop
    //long startTime = System.nanoTime();
    BufferedImage capture = getImage();
//    h = capture.getHeight();
//    w = capture.getWidth();
//    imageLen = w*h;
//    dataBuffInt = capture.getRGB(0, 0, w, h, null, 0, w);
    int[] color = ColorThief.getColor(capture,5,true);
//    int[] redz = new int[imageLen];
//    int[] greenz = new int[imageLen];
//    int[] bluez = new int[imageLen];
//    
//    for (int i=0; i<dataBuffInt.length;i++){
//        Color c1 = new Color(dataBuffInt[i]);
//        Color c = c1.brighter();
//        redz[i] = c.getRed();
//        greenz[i] = c.getGreen();
//        bluez[i] = c.getBlue();
//        }
//
//    for (int i = 0;i<imageLen; i++){
//        sumR += redz[i];
//        sumG += greenz[i];
//        sumB += bluez[i];
//    }
//    avgR = sumR/imageLen;
//    avgG = sumG/imageLen;
//    avgB = sumB/imageLen;
    String values = color[0]+","+color[1]+","+color[2]+".";
//    buffer[0]= (byte) avgR;
//    buffer[1]= (byte) avgG;
//    buffer[2]= (byte) avgB;
    buffer = values.getBytes();
    //System.out.println(values);
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


