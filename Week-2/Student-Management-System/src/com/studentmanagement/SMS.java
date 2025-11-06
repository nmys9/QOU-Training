package com.studentmanagement;

import java.util.HashMap;

public class SMS {
    private HashMap<Integer,Student> students;
    private HashMap<Integer,Course> courses;

    public SMS(){
        this.students=new HashMap<>();
        this.courses=new HashMap<>();
    }

    public void addStudent(int id,String studentName){
        if(!(students.containsKey(id))){
            Student student=new Student(id,studentName);
            students.put(id,student);
            System.out.println("Student Added Successfully.");
        }else{
            System.out.println("Error: Student ID already exists.");
        }
    }

    public void addCourse(int id,String courseName){
        if(!(courses.containsKey(id))){
            Course course=new Course(id,courseName);
            courses.put(id,course);
            System.out.println("Course Added Successfully.");
        }else{
            System.out.println("Error: Course ID already exists.");
        }
    }

    public void addEnrollment(int studentID,int courseID,double grade){
        Student student=students.get(studentID);
        student.addEnrollment(courseID,grade);
    }

    public void showStudents(){
        if(students.isEmpty()){
            System.out.println("No Student yet.");
        }else{
            for(Student student: students.values()){
                System.out.println(student);
                student.getEnrollment().forEach((courseID,grade)->{
                    System.out.println("Grade of "+ getCourse(courseID).getNameCourse()+" : "+grade);
                });
                System.out.println("-----------------------------------");
            }
        }
    }

    public void showCourse(){
        if(courses.isEmpty()){
            System.out.println("No Courses yet");
        }else{
            for(Course course: courses.values()){
                System.out.println(course);
            }
        }
    }

    public void showCalcGPA(int studentID){
        Student student=students.get(studentID);
        System.out.println("Student GPA is "+ student.calcGPA());
    }

    public Student getStudent(int id){
        return students.get(id);
    }

    public Course getCourse(int id){
        return courses.get(id);
    }

    public void removeStudent(int id){
        if(students.containsKey(id)){
            students.remove(id);
            System.out.println("Remove Student Successfully");
        }else{
            System.out.println("Error: Student ID not found ");
        }
    }

}
