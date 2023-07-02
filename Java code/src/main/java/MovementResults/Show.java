/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MovementResults;
import Objects.Movement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.*;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
/**
 *
 * @author jaime
 */
public class Show implements Initializable{
    int i, j, k;

    Random random = new Random();
    
    String filePath = "C:/Users/jaime/OneDrive/Escritorio/TFG/Recordings/Patient 1/NOR27.txt";
    
    @FXML
    private LineChart<Number, Number> lineChart;
    

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){     

        Movement movement = createArrays(filePath);
        List<List<Number>> listOfVectorsShoulder = movement.getListOfVectorsShoulder();
        List<List<Number>> listOfVectorsElbow = movement.getListOfVectorsElbow();
        List<List<Number>> listOfVectorsWrist = movement.getListOfVectorsWrist();
        movement.setListOfAngles(computeAnglesAndCharacteristics(listOfVectorsShoulder, listOfVectorsElbow, listOfVectorsWrist));
        
        new Thread(()-> {
            int size = listOfVectorsShoulder.size();
            // Check is bigger than any of the lists
            for (j = 0; j < size; j++){
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                List<Number> fila1 = listOfVectorsShoulder.get(j);
                List<Number> fila2 = listOfVectorsElbow.get(j);
                List<Number> fila3 = listOfVectorsWrist.get(j);
                Platform.runLater(()-> { 
                    lineChart.getData().clear();
                    series.getData().add(new XYChart.Data<Number,Number>(fila1.get(0), fila1.get(1)));
                    series.getData().add(new XYChart.Data<Number,Number>(fila2.get(0), fila2.get(1)));
                    series.getData().add(new XYChart.Data<Number,Number>(fila3.get(0), fila3.get(1)));
                    lineChart.getData().add(series);                
                });
                try {
                    Thread.sleep(100); // Wait 40 miliseconds as it is the mean time past between two instances
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }            
        }).start();
    }
    
    private Movement createArrays(String filePath){    
        // List of list of integers is created
        List<List<Number>> listOfVectorsShoulder = new ArrayList<>();
        List<List<Number>> listOfVectorsWrist = new ArrayList<>();
        List<List<Number>> listOfVectorsElbow = new ArrayList<>();
        
        String aux, aux2;
        String[] separate;
        String[] separate2;
        int i, j, k;       

        Scanner s = null;
        File file = new File(filePath);
        try{
            s = new Scanner(file);
            while(s.hasNextLine()){
                aux = s.nextLine();
                if (!aux.isEmpty()){
                    aux = aux.substring(1);//to ignore the ID hyphen
                }
                
                //Replace IDs by articulations names                
                aux = aux.replace("1:","shoulder:");
                aux = aux.replace("2:","elbow:");
                aux = aux.replace("3:","wrist:");
                
                // When all the TAGS have been detected
                if(aux.contains("shoulder") && aux.contains("elbow")&& aux.contains("wrist")){
                    separate = aux.split("-");
                    for(i=0; i<3;  i++){
                        switch(separate[i].substring(0,1)){ 
                                            case "s":
                                                aux2 = separate[i].substring(10);
                                                separate2 = aux2.split(";");
                                                int x1 = Integer.parseInt(separate2[0]);
                                                int y1 = Integer.parseInt(separate2[1]);
                                                List<Number> row1 = new ArrayList<>();
                                                row1.add(x1);
                                                row1.add(-y1 +240);
                                                listOfVectorsShoulder.add(row1);
                                                break;
                                            case "e":
                                                aux2 = separate[i].substring(7);
                                                separate2 = aux2.split(";");
                                                int x2 = Integer.parseInt(separate2[0]);
                                                int y2 = Integer.parseInt(separate2[1]);
                                                List<Number> row2 = new ArrayList<>();
                                                row2.add(x2);
                                                row2.add(-y2 +240);
                                                listOfVectorsWrist.add(row2);
                                                break;
                                            case "w":
                                                aux2 = separate[i].substring(7);
                                                separate2 = aux2.split(";");
                                                int x3 = Integer.parseInt(separate2[0]);
                                                int y3 = Integer.parseInt(separate2[1]);
                                                List<Number> row3 = new ArrayList<>();
                                                row3.add(x3);
                                                row3.add(-y3 +240);
                                                listOfVectorsElbow.add(row3);
                                                break;                                                
                                        }
                                        
                                    }
                }
                
                
                // When only elbow and wrist TAGS have been detected
                else if(aux.contains("elbow")&& aux.contains("wrist")){
                    separate = aux.split("-");

                    
                    //Shoulder position is inserted automatically
                    if(listOfVectorsShoulder.size() > 0){
                        listOfVectorsShoulder.add(listOfVectorsShoulder.get(listOfVectorsShoulder.size()-1));
                    }
                    
                    
                    for(i=0; i<2; i++){
                        switch(separate[i].substring(0,1)){ 
                                            case "e":
                                                aux2 = separate[i].substring(7);
                                                separate2 = aux2.split(";");
                                                int x2 = Integer.parseInt(separate2[0]);
                                                int y2 = Integer.parseInt(separate2[1]);
                                                List<Number> row2 = new ArrayList<>();
                                                row2.add(x2);
                                                row2.add(-y2 +240);
                                                listOfVectorsWrist.add(row2);
                                                break;
                                            case "w":
                                                aux2 = separate[i].substring(7);
                                                separate2 = aux2.split(";");
                                                int x3 = Integer.parseInt(separate2[0]);
                                                int y3 = Integer.parseInt(separate2[1]);
                                                List<Number> row3 = new ArrayList<>();
                                                row3.add(x3);
                                                row3.add(-y3 +240);
                                                listOfVectorsElbow.add(row3);
                                                break;                                                
                                        }
                                        
                                    }
                }
                
                // When only shoulder and wrist TAGS have been detected
                else if(aux.contains("shoulder")&& aux.contains("wrist")){
                    separate = aux.split("-");

                    
                    //Shoulder position is inserted automatically
                    if(listOfVectorsElbow.size() > 0){
                        listOfVectorsElbow.add(listOfVectorsElbow.get(listOfVectorsElbow.size()-1));
                    }
                    
                    
                    for(i=0; i<2 ;  i++){
                        switch(separate[i].substring(0,1)){ 
                                            case "s":
                                                aux2 = separate[i].substring(10);
                                                separate2 = aux2.split(";");
                                                int x1 = Integer.parseInt(separate2[0]);
                                                int y1 = Integer.parseInt(separate2[1]);
                                                List<Number> row1 = new ArrayList<>();
                                                row1.add(x1);
                                                row1.add(-y1 +240);
                                                listOfVectorsShoulder.add(row1);
                                                break;
                                            case "w":
                                                aux2 = separate[i].substring(7);
                                                separate2 = aux2.split(";");
                                                int x3 = Integer.parseInt(separate2[0]);
                                                int y3 = Integer.parseInt(separate2[1]);
                                                List<Number> row3 = new ArrayList<>();
                                                row3.add(x3);
                                                row3.add(-y3+240);
                                                listOfVectorsWrist.add(row3);
                                                break;                                                
                                        }
                                        
                                    }
                }
                
                // When only shoulder and elbow TAGS have been detected
                else if(aux.contains("shoulder")&& aux.contains("elbow")){
                    separate = aux.split("-");

                    
                    //Wrist position is inserted automatically
                    if(listOfVectorsWrist.size() >0){
                        listOfVectorsWrist.add(listOfVectorsWrist.get(listOfVectorsWrist.size()-1));
                    }
                    
                    
                    for(i=0; i<2;  i++){
                        switch(separate[i].substring(0,1)){ 
                                            case "s":
                                                aux2 = separate[i].substring(10);
                                                separate2 = aux2.split(";");
                                                int x1 = Integer.parseInt(separate2[0]);
                                                int y1 = Integer.parseInt(separate2[1]);
                                                List<Number> row1 = new ArrayList<>();
                                                row1.add(x1);
                                                row1.add(-y1 +240);
                                                listOfVectorsShoulder.add(row1);
                                                break;
                                            case "e":
                                                aux2 = separate[i].substring(7);
                                                separate2 = aux2.split(";");
                                                int x2 = Integer.parseInt(separate2[0]);
                                                int y2 = Integer.parseInt(separate2[1]);
                                                List<Number> row2 = new ArrayList<>();
                                                row2.add(x2);
                                                row2.add(-y2 +240);
                                                listOfVectorsElbow.add(row2);
                                                break;                                               
                                        }
                                        
                                    }
                }
                
                
            }
       
        }catch(IOException ex){
            ex.printStackTrace();
        }        
        
        Movement movement = new Movement(listOfVectorsShoulder, listOfVectorsElbow, listOfVectorsWrist);
        return movement;
    }
    
    private List<Double> computeAnglesAndCharacteristics(List<List<Number>> listOfVectorsShoulder, List<List<Number>> listOfVectorsElbow, List<List<Number>> listOfVectorsWrist){
        // Compute the distance of the triangle sides
        List<Double> listOfAngles = new ArrayList<>();        
        Double max = Double.MIN_VALUE;
        Double min = Double.MAX_VALUE;
        Double rom = Double.MIN_VALUE;
        for(int i = 0; i < listOfVectorsShoulder.size() || i < listOfVectorsElbow.size() || i < listOfVectorsWrist.size(); i++){
            List<Number> rowShoulder = listOfVectorsShoulder.get(i);
            List<Number> rowElbow = listOfVectorsElbow.get(i);
            List<Number> rowWrist = listOfVectorsWrist.get(i);
            
        // Compute how long the size are
        double sideShoulderElbow = (double) Math.sqrt(Math.pow(rowElbow.get(0).intValue() - rowShoulder.get(0).intValue(), 2) + Math.pow(rowElbow.get(1).intValue() - rowShoulder.get(1).intValue(), 2));
        double sideElbowWrist = (double) Math.sqrt(Math.pow(rowWrist.get(0).intValue() - rowElbow.get(0).intValue(), 2) + Math.pow(rowWrist.get(1).intValue() - rowElbow.get(1).intValue(), 2));
        double sideWristShoulder = (double) Math.sqrt(Math.pow(rowShoulder.get(0).intValue() - rowWrist.get(0).intValue(), 2) + Math.pow(rowShoulder.get(1).intValue() - rowWrist.get(1).intValue(), 2));
        System.out.println("Sides: " + sideElbowWrist + ", " + sideShoulderElbow + ", " + sideWristShoulder);
            
        // Compute de list of angles for each instance, ONLY when the three distances are over 0
        if(sideShoulderElbow > 0 && sideElbowWrist > 0 && sideWristShoulder > 0 ){
            double numerator = ((Math.pow(sideShoulderElbow, 2)) + (Math.pow(sideElbowWrist, 2)) - (Math.pow(sideWristShoulder, 2)));
            double denominator = (2*sideShoulderElbow*sideElbowWrist);
            double bendingAngle = (double) Math.toDegrees(Math.acos(numerator/denominator));
                
            //To compute the maximum and minimum angle, and consequently the ROM
            if(bendingAngle > max) max = bendingAngle;
                else if(bendingAngle < min) min = bendingAngle;
                if((max-min)> rom) rom = max-min;
                System.out.println("The angle is: " + bendingAngle);
                System.out.println("The maximum angle is: " + max + "\nThe minimum angle is: " + min + "\nThe range of movement achieved so far is: " + rom);
                listOfAngles.add(bendingAngle); 
            }
        System.out.println("\n\n");
                      
        }
            

        //Print the list of angles
        for(int i = 0; i < listOfAngles.size(); i++){
            System.out.println(listOfAngles.get(i));                
        }
        
        return listOfAngles;
    }
    
    
}
