/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MovementResults;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static javafx.application.Application.launch;

/**
 *
 * @author jaime
 */
public class CountingFiles {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "C:/Users/jaime/OneDrive/Escritorio/TFG/Recordings/Patient 3/ORT60.txt", aux ="" ;
        File file = new File(filePath);
        int total = 0, shoulder = 0, elbow = 0, wrist = 0;
        Scanner s = new Scanner(file);
            while(s.hasNextLine()){
                total++;
                aux = s.nextLine();
                if(aux.contains("-2"))wrist++;
                if(aux.contains("-1"))elbow++;
                if(aux.contains("-3"))shoulder++;
                
                
                
                
            }
            System.out.println("Shoulder: " + shoulder + "\nelbow: " + elbow + "\nWrist: " + wrist + "\nTotal: " + total);
    }
}
