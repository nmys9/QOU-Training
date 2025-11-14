package com.studentmanagement.service;

import com.studentmanagement.model.Course;
import com.studentmanagement.model.Doctor;
import com.studentmanagement.repository.CourseRepository;
import com.studentmanagement.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CourseService {
    private final CourseRepository courseRepository;
    private final DoctorService doctorService;

    public CourseService(CourseRepository courseRepository,DoctorService doctorService){
        this.courseRepository=courseRepository;
        this.doctorService=doctorService;
    }

    public Course getOrCreateCourse(String courseName, int creditHours, Doctor doctor){
        Optional<Course> existsCourse=courseRepository.getCourseByName(courseName);
        if(existsCourse.isPresent()){
            return existsCourse.get();
        }else{
            validateCourseName(courseName);
            validateCreditHours(creditHours);
            doctorService.getDoctorByID(doctor.getDoctorID());
            return courseRepository.addCourse(courseName,creditHours,doctor);
        }
    }

    public Course updateCourse(int courseID,String courseName, int creditHours, Doctor doctor){
        getCourseByID(courseID);
        validateCourseName(courseName);
        validateCreditHours(creditHours);
        doctorService.getDoctorByID(doctor.getDoctorID());
        boolean updateSuccess=courseRepository.updateCourse(courseID,courseName,creditHours,doctor);
        if(updateSuccess){
            return getCourseByID(courseID);
        }else{
            throw new IllegalStateException("Failed to update Course with ID " + courseID + " due to unexpected Repository error.");
        }
    }

    public List<Course> getAllCourses(){
        return courseRepository.getAllCourses();
    }

    public Course getCourseByID(int courseID){
        validateCourseID(courseID);
        return courseRepository.getCourseByID(courseID)
                .orElseThrow(() -> new NoSuchElementException(
                        "Course with ID "+ courseID +" cannot be found"));
    }

    public Course getCourseByName(String courseName){
        validateCourseName(courseName);
        return courseRepository.getCourseByName(courseName)
                .orElseThrow(() -> new NoSuchElementException(
                        "Course with name "+ courseName +" cannot be found."));
    }

    public List<Course> getCoursesByDoctorID(int doctorID){
        doctorService.getDoctorByID(doctorID);
        return courseRepository.getCoursesByDoctorID(doctorID);
    }


    public void validateCourseID(int courseID){
        if(courseID<=0){
            throw new IllegalArgumentException("Course ID must be positive number");
        }
    }
    public void validateCourseName(String courseName){
        if (courseName == null || courseName.trim().length() < 3) {
            throw new IllegalArgumentException("Course name for search cannot be null and must be at least 3 characters long.");
        }
    }
    public void validateCreditHours(int creditHours){
        if(creditHours<=0){
            throw new IllegalArgumentException("Credit Hours for Course cannot be Zero");
        }
    }


}
