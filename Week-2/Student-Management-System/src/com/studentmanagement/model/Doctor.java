package com.studentmanagement.model;

public class Doctor {
    private int doctorID;
    private String doctorName;

    public Doctor(){}

    public Doctor(int doctorID, String doctorName){
        this.doctorID = doctorID;
        setDoctorName(doctorName);
    }

    public void setDoctorName(String doctorName) {
        if(doctorName == null || doctorName.trim().isEmpty()){
            throw new IllegalArgumentException("Doctor name cannot be null or empty");
        }
        this.doctorName = doctorName;
    }

    public int getDoctorID() {return doctorID;}
    public String getDoctorName() {return doctorName;}

    @Override
    public String toString() {
        return String.format("%d - %s",this.doctorID,this.doctorName);
    }
}
