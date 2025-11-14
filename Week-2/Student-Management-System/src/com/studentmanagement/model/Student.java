package com.studentmanagement.model;

public class Student {
    private int studentID;
    private String studentName;

    public Student(){

    }

    public Student(int studentID, String studentName){
        this.studentID = studentID;
        setStudentName(studentName);
    }

    public void setStudentName(String studentName) {
        if(studentName==null || studentName.trim().isEmpty()){
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        this.studentName = studentName;
    }

    public int getStudentID() {return studentID;}
    public String getStudentName() {return studentName;}

    @Override
    public String toString() {
        return String.format("ID: %d- Name: %s",this.studentID,this.studentName);
    }
}
