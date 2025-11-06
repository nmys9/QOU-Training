package com.studentmanagement;

public class Course {
    private int id;
    private String nameCourse;

    public Course(){}
    public Course(int id,String nameCourse){
        this.id=id;
        this.nameCourse=nameCourse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    @Override
    public String toString() {
        return String.format("Course ID : %d - Course Name : %s",this.id,this.nameCourse);
    }
}
