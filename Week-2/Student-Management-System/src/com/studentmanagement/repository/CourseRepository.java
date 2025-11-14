package com.studentmanagement.repository;

import com.studentmanagement.model.Course;
import com.studentmanagement.model.Doctor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CourseRepository {
    private final Map<Integer, Course> courses=new HashMap<>();
    private final AtomicInteger idCounter=new AtomicInteger(1);

    public Course addCourse(String courseName, int creditHours,Doctor doctor){
        int id=idCounter.getAndIncrement();
        Course newCourse=new Course(id,courseName,creditHours,doctor);
        courses.put(id,newCourse);
        return newCourse;
    }

    public boolean updateCourse(int id,String courseName, int creditHours,Doctor doctor){
        Course exists=courses.get(id);
        if(exists != null){
            Course updateCourse=new Course(id,courseName,creditHours,doctor);
            courses.put(id,updateCourse);
            return true;
        }
        return false;
    }

    public boolean removeCourse(int id){
        return courses.remove(id) != null;
    }

    public List<Course> getAllCourses(){
        return courses.values().stream()
                .sorted(Comparator.comparing(Course::getCourseID))
                .toList();
    }

    public Optional<Course> getCourseByID(int courseID){
        return courses.values().stream()
                .filter(c -> c.getCourseID() == courseID).
                findFirst();
    }

    public Optional<Course> getCourseByName(String courseName){
        return courses.values().stream()
                .filter(c -> c.getCourseName().equalsIgnoreCase(courseName))
                .findFirst();
    }

    public List<Course> getCoursesByDoctorID(int id){
        return courses.values().stream()
                .filter(c -> c.getDoctor().getDoctorID()== id).
                toList();
    }

    public boolean courseExists(int id){
        return courses.containsKey(id);
    }
}
