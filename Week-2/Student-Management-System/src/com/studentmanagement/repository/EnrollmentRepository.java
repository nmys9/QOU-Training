package com.studentmanagement.repository;

import com.studentmanagement.model.Course;
import com.studentmanagement.model.Enrollment;
import com.studentmanagement.model.Student;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EnrollmentRepository {
    private final List<Enrollment> enrollments=new ArrayList<>();
    private final AtomicInteger idCounter=new AtomicInteger(1);

    public Enrollment addEnrollment(Student student, Course course, LocalDate enrolDate){
        int id=idCounter.getAndIncrement();
        Enrollment newEnrollment=new Enrollment(id,student,course,enrolDate);
        enrollments.add(newEnrollment);
        return newEnrollment;
    }

    public boolean updateGrade(int enrollID,Double grade){
       Optional<Enrollment> existsEnrollment=getEnrollmentByID(enrollID);

       if(existsEnrollment.isPresent()){
           Enrollment enrollment=existsEnrollment.get();
           enrollment.setGrade(grade);
           return true;
       }
       return false;

    }
    public boolean removeEnrollment(int enrollID){
        return enrollments.removeIf(e -> e.getEnrolID()==enrollID);
    }

    public List<Enrollment> getAllEnrollment(){
        return new ArrayList<>(enrollments);
    }

    public Optional<Enrollment> getEnrollmentByID(int enrollID){
        return enrollments.stream()
                .filter(e -> e.getEnrolID()==enrollID)
                .findFirst();
    }

    public List<Enrollment> getEnrollmentsByStudentID(int studentID){
        return enrollments.stream()
                .filter(e -> e.getStudent().getStudentID()==studentID)
                .toList();
    }

    public List<Enrollment> getEnrollmentsStudentByCourseID(int courseID){
        return enrollments.stream()
                .filter(e -> e.getCourse().getCourseID()==courseID)
                .toList();
    }

    public List<Enrollment> getEnrollmentsByStudentAndCourse(int studentID,int courseID){
        return enrollments.stream()
                .filter(e -> e.getStudent().getStudentID()==studentID)
                .filter(e->e.getCourse().getCourseID()==courseID)
                .toList();
    }

    public Optional<Enrollment> getLatestActiveEnrollment(int studentID,int courseID){
        return enrollments.stream()
                .filter(e -> e.getStudent().getStudentID()==studentID)
                .filter(e->e.getCourse().getCourseID()==courseID)
                .filter(e -> e.getGrade()==null)
                .sorted(Comparator.comparing(Enrollment::getEnrolDate).reversed())
                .findFirst();
    }





}
