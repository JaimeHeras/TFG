/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readInfoFromArduino;

import comunicacionserial.ArduinoExcepcion;
import comunicacionserial.ComunicacionSerial_Arduino;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintStream;
import java.lang.*;
import java.util.Scanner;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
/**
 *
 * @author jaime
 */


public class readInfoFromArduino {
    public static void main(String[] args) throws IOException {
        // The file where the information received is stored
        String FILEPATH = "C:/Users/jaime/OneDrive/Escritorio/TFG/Recordings/3.txt";
        // The recording time must be indicated in miliseconds
        int TIME = 2000;
        // The port with which the Arduino Micro communicates, it must be configured previously in Arduino IDE
        String PORT = "COM10";
        ComunicacionSerial_Arduino connection = new ComunicacionSerial_Arduino();
        SerialPortEventListener listen;
        listen = new SerialPortEventListener() {
            long startTime = System.currentTimeMillis();
            PrintWriter writer;            
            Scanner s = null;
            File file = new File(FILEPATH);
            
            // To write in a file the obtained information from the Arduino
            {
                try {
                    writer = new PrintWriter(new FileWriter(FILEPATH));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            @Override
            public void serialEvent(SerialPortEvent spe) {
                try {
                    // Checks if the connection to the Arduino has been established
                    if (connection.isMessageAvailable()) {
                        writer.println(connection.printMessage());
                        //It receives and writes in the file information up to a specified time
                        if (System.currentTimeMillis() - startTime >= TIME) {
                            writer.close();
                            System.exit(0);
                        }
                    }
                } catch (SerialPortException ex) {
                    ex.printStackTrace();
                } catch (ArduinoExcepcion ex) {
                    ex.printStackTrace();
                }finally{
                    try{
                        if(s != null){        
                            s.close();
                        }
                        
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        };

        try {
            // The connection with Arduino MIcro is established, with a velocity of 9600 bits per second to the indicated port
            // Constantly receiving (listen) everything sent by the Arduino Micro
            connection.arduinoRXTX(PORT, 9600, listen);
        } catch (ArduinoExcepcion ex) {
            ex.printStackTrace();
        }
    }
}