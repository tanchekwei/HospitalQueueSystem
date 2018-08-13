/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.time.*;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author JT
 */
public class PatientInSeeDoctorQueue {
    private int queueNo;
    private int levelOfSickness;
    private double priority;
    private Date arrivalTime;
    private Patients patient;
    private static int nextQueueNo = 10000;
    
    public PatientInSeeDoctorQueue(int levelOfSickness, Patients patient) {
        this.queueNo = nextQueueNo++;
        this.levelOfSickness = levelOfSickness;
        this.arrivalTime = new Date();
        this.patient = patient;
        this.priority = Math.round(calPriority() * 100.0) / 100.0;
    }

    public double calPriority() {
        // Formula: [(arrivalMin + levelOfSickness)/(Maximum Minutes + Highest Level Of Sickness)] * 100
//        int tempSick = levelOfSickness;
//        Date currTime = new Date();
//        double arrivalMin;
//        double diffHour = currTime.getHours() - currTime.getHours();
//        if(diffHour == 0.0) {
//            arrivalMin = currTime.getMinutes() - arrivalTime.getMinutes();
//        } else {
//            if(currTime.getMinutes() > arrivalTime.getMinutes()) {
//                arrivalMin = currTime.getMinutes() - arrivalTime.getMinutes();
//            } else {
//                diffHour -= 1.0;
//                arrivalMin = (currTime.getMinutes() + 60.0) - arrivalTime.getMinutes();
//                arrivalMin += diffHour * 60.0;
//            }
//        }
//        return (arrivalMin + tempSick) * 100.0/(63.0);
        double totalPerc = 0.0;
        // By time.
        Date currTime = new Date();
        double arrivalDurationInSec = 0.0;
        double diffHrs = currTime.getHours() - getArrivalTime().getHours();
        double diffMin = 0.0;
        double diffSec = 0.0;
        
        if(currTime.getSeconds() > getArrivalTime().getSeconds()) {
            diffSec = currTime.getSeconds() - getArrivalTime().getSeconds();
        } else {
            diffMin -= 1.0;
            diffSec += (60.0 + currTime.getSeconds()) - getArrivalTime().getSeconds();
        }
        arrivalDurationInSec += diffSec;
        
        if(currTime.getMinutes() > getArrivalTime().getMinutes()) {
            diffMin += currTime.getMinutes() - getArrivalTime().getMinutes();
        } else {
            diffHrs -= 1.0;
            diffMin += (60.0 + currTime.getMinutes()) - getArrivalTime().getMinutes();
        }
        arrivalDurationInSec += (60.0 * diffMin);
        
        arrivalDurationInSec = arrivalDurationInSec + 60 * 60 * diffHrs;
        
        totalPerc += arrivalDurationInSec * 100.0 / 3600.00;
        
        // By Sickness.
        totalPerc += getLevelOfSickness() * 100.0 / 3.0;
        
        // By age.
            // Get patient age.
        Calendar c = Calendar.getInstance();
        double currYear = c.get(Calendar.YEAR) - 2000.0;
        double patientYOB = (getPatient().getPatientic().charAt(0) - 48.0) * 10.0 + (getPatient().getPatientic().charAt(1) - 48.0);
        double patientAge = patientYOB;
        if(currYear >= patientYOB) { // If less than, means patient below or equals 18 years old.
            // Do nothing. Remain prefix in 2 characters in IC as his/her age.
        } else { // Else, more than 18 years old.
            patientAge = 100.0 - patientAge + currYear;
        }
            // Calculate priority by age. By using y = x^2 function.
        if(patientAge <= 12) {
            patientAge = 100.0 - patientAge;
        }
        totalPerc += Math.pow(patientAge, 2) * 100.0 / Math.pow(100.0, 2);
        
        return totalPerc;
    }
    
    public int getQueueNo() {
        return queueNo;
    }

    public double getPriority() {
        return calPriority();
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Patients getPatient() {
        return patient;
    }

    public int getLevelOfSickness() {
        return levelOfSickness;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public void setLevelOfSickness(int levelOfSickness) {
        this.levelOfSickness = levelOfSickness;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (Math.round(priority));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PatientInSeeDoctorQueue other = (PatientInSeeDoctorQueue) obj;
        if (this.queueNo != other.queueNo) {
            return false;
        }
        if (this.levelOfSickness != other.levelOfSickness) {
            return false;
        }
        if (Double.doubleToLongBits(this.priority) != Double.doubleToLongBits(other.priority)) {
            return false;
        }
        if (!Objects.equals(this.arrivalTime, other.arrivalTime)) {
            return false;
        }
        if (!Objects.equals(this.patient, other.patient)) {
            return false;
        }
        return true;
    }
    
    public String toString() {
        return Double.toString(priority);
    }
}
