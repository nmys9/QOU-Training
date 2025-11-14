package com.studentmanagement.model;

import java.time.LocalDate;
import java.util.Objects;

public class Enrollment {
    private int enrolID;
    private Student student;
    private Course course;
    private LocalDate enrolDate;
    private Double grade;


    public Enrollment(){}

    public Enrollment(int enrolID,Student student,Course course,LocalDate enrolDate){
        this.enrolID=enrolID;
        this.student= Objects.requireNonNull(student,"Student cannot be null");
        this.course=Objects.requireNonNull(course,"Course cannot be null");
        setEnrolDate(enrolDate);
        this.grade=null;
    }

    public Enrollment(int enrolID,Student student,Course course,LocalDate enrolDate,Double grade) {
        this(enrolID, student, course,enrolDate);
        setGrade(grade);
    }

    public void setGrade(Double grade) {
        if(grade<0 || grade>100){
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        this.grade = grade;
    }

    private void setEnrolDate(LocalDate enrolDate) {
        Objects.requireNonNull(enrolDate,"Date cannot be null");
        if(enrolDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Enrollment Date cannot be in the future.");
        }
        this.enrolDate = enrolDate;
    }

    public int getEnrolID() {return enrolID;}
    public Student getStudent() {return student;}
    public Course getCourse() {return course;}
    public Double getGrade() {return grade;}
    public LocalDate getEnrolDate() {return enrolDate;}

    @Override
    public String toString() {
        return String.format("%s : (%s :%d)",
                student.getStudentName(),
                course.getCourseName(),
                this.grade
        );
    }
}
