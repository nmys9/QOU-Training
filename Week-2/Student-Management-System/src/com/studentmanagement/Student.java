package com.studentmanagement;
import java.util.HashMap;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private HashMap<Integer, Double> enrollment;

    Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.enrollment = new HashMap<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public HashMap<Integer, Double> getEnrollment() {
        return enrollment;
    }

    @Override
    public String toString() {
        return String.format("Student ID: %d Student name: %s", this.id, this.name);
    }

    public void addEnrollment(int courseID, double grade) {
        enrollment.put(courseID, grade);
    }


    public double calcGPA(){
        double avg,sum=0.0;
        for(double grade: enrollment.values()){
            sum+=grade;
        }
        avg=sum/enrollment.size();
        return  Double.parseDouble(String.format("%.2f",avg));
    }

}