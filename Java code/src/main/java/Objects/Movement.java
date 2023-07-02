/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 *
 * @author jaime
 */
public class Movement {
    private List<List<Number>> listOfVectorsShoulder;
    private List<List<Number>> listOfVectorsElbow;
    private List<List<Number>> listOfVectorsWrist;
    
    private List<Double> listOfAngles;
    private double maxAngle;
    private double minAngle;
    private double rangeOfMovement;
    
    public Movement(List<List<Number>> listOfVectorsShoulder, List<List<Number>> listOfVectorsElbow, List<List<Number>> listOfVectorsWrist) {
        this.listOfVectorsShoulder = listOfVectorsShoulder;
        this.listOfVectorsElbow = listOfVectorsElbow;
        this.listOfVectorsWrist = listOfVectorsWrist;
        
    }
    
    public Movement(){
    }

    public void setListOfVectorsShoulder(List<List<Number>> listOfVectorsShoulder) {
        this.listOfVectorsShoulder = listOfVectorsShoulder;
    }

    public void setListOfVectorsElbow(List<List<Number>> listOfVectorsElbow) {
        this.listOfVectorsElbow = listOfVectorsElbow;
    }

    public void setListOfVectorsWrist(List<List<Number>> listOfVectorsWrist) {
        this.listOfVectorsWrist = listOfVectorsWrist;
    }

    
   

    public void setMaxAngle(double maxAngle) {
        this.maxAngle = maxAngle;
    }

    public void setMinAngle(double minAngle) {
        this.minAngle = minAngle;
    }

    public void setRangeOfMovement(double rangeOfMovement) {
        this.rangeOfMovement = rangeOfMovement;
    }

    public void setListOfAngles(List<Double> listOfAngles) {
        this.listOfAngles = listOfAngles;
    }

    public List<List<Number>> getListOfVectorsShoulder() {
        return listOfVectorsShoulder;
    }

    public List<List<Number>> getListOfVectorsElbow() {
        return listOfVectorsElbow;
    }

    public List<List<Number>> getListOfVectorsWrist() {
        return listOfVectorsWrist;
    }
    
    public List<Double> getListOfAngles() {
        return listOfAngles;
    }

    public double getMaxAngle() {
        return maxAngle;
    }

    public double getMinAngle() {
        return minAngle;
    }

    public double getRangeOfMovement() {
        return rangeOfMovement;
    }

    
    
}
