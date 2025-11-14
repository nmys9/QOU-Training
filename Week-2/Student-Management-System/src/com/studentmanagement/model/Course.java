package com.studentmanagement.model;

import java.util.Objects;

public class Course {
    private int courseID;
    private String courseName;
    private int creditHours;
    private Doctor doctor;

    public Course(){}

    public Course(int courseID, String courseName,int creditHours){
        this.courseID = courseID;
        setCourseName(courseName);
        setCreditHours(creditHours);
    }

    public Course(int courseID, String courseName,int creditHours, Doctor doctor){
        this(courseID,courseName,creditHours);
        setDoctor(doctor);
    }

    public void setCourseName(String courseName) {
        if(courseName ==null || courseName.trim().isEmpty()){
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        this.courseName = courseName;
    }

    public void setCreditHours(int creditHours) {
        if(creditHours<=0){
            throw new IllegalArgumentException("Credit Hours must be positive number(greater than zero).");
        }
        this.creditHours = creditHours;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor= Objects.requireNonNull(doctor,"Doctor cannot be null.");
    }

    public int getCourseID() {return courseID;}
    public String getCourseName() {return courseName;}
    public int getCreditHours() {return creditHours;}
    public Doctor getDoctor() {return doctor;}

    @Override
    public String toString() {
        return String.format("%d - %s", this.courseID,this.courseName);
    }
}
