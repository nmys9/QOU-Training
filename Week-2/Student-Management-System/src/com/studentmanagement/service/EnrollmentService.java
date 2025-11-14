package com.studentmanagement.service;

import com.studentmanagement.model.Course;
import com.studentmanagement.model.Enrollment;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.EnrollmentRepository;

import java.util.*;
import java.time.LocalDate;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentService studentService,
                             CourseService courseService){
        this.enrollmentRepository=enrollmentRepository;
        this.studentService=studentService;
        this.courseService=courseService;
    }

    //getOrCreateEnrollment
    //
    public Enrollment getOrCreateEnrollment(int studentID, int courseID){
        Student student=studentService.getStudentByID(studentID);
        Course course=courseService.getCourseByID(courseID);

        Optional<Enrollment> existsEnrollment=enrollmentRepository.getLatestActiveEnrollment(studentID,courseID);
        if(existsEnrollment.isPresent()){
            return existsEnrollment.get();
        }
        LocalDate enrolmentDate=LocalDate.now();
        Enrollment enrollment= enrollmentRepository.addEnrollment(student,course,enrolmentDate);
        return enrollment;
    }
    //update
    //
    public Enrollment updateGrade(int studentID,int courseID,double grade){
        validateGrade(grade);

        Enrollment existsEnrollment = getOrCreateEnrollment(studentID, courseID);
        int enrolmentID = existsEnrollment.getEnrolID();

        boolean updateGrade= enrollmentRepository.updateGrade(enrolmentID,grade);
        if(updateGrade){
            return getEnrollmentByID(enrolmentID);
        }else{
            throw new IllegalStateException("Failed to update Grade with ID " + enrolmentID + " due to unexpected Repository error");
        }
    }

    //getAllEnrollment
    //
    public List<Enrollment> getAllEnrollment(){
        return enrollmentRepository.getAllEnrollment();
    }

    //getEnrollmentByID
    //
    public Enrollment getEnrollmentByID(int enrolmentID){
        return enrollmentRepository.getEnrollmentByID(enrolmentID)
                .orElseThrow(() -> new NoSuchElementException(
                        "Enrollment with ID " + enrolmentID + " cannot be found"
                ));
    }

    //getStudentEnrollments(List)
    //
    public List<Enrollment> getEnrollmentsForStudent(int studentID){
        studentService.getStudentByID(studentID);
        return enrollmentRepository.getEnrollmentsByStudentID(studentID);
    }

    //get Enrollments Students in The Course(list)
    //
    public List<Enrollment> getEnrollmentsStudentsInTheCourse(int courseID){
        courseService.getCourseByID(courseID);
        return enrollmentRepository.getEnrollmentsStudentByCourseID(courseID);
    }

    //getAvgForStudent
    //
    public double getAvgForStudent(int studentID){
        List<Enrollment> allEnrollments=getEnrollmentsForStudent(studentID);

        List<Enrollment> gradedEnrollments= allEnrollments.stream()
                .filter(e -> e.getGrade() != null)
                .toList();
        double sumOfGrades=gradedEnrollments.stream()
                .mapToDouble(e -> e.getGrade() * e.getCourse().getCreditHours())
                .sum();

        int totalCredit=gradedEnrollments.stream()
                .mapToInt(e -> e.getCourse().getCreditHours())
                .sum();

        if(totalCredit==0){
            throw new NoSuchElementException(
                    "Cannot calculate average for student ID " + studentID +
                            " because no graded courses (total credit hours = 0) were found."
            );
        }
        return sumOfGrades/totalCredit;
    }

    //getSumCreditHoursForStudent
    //
    public int getSumCreditHoursForStudent(int studentID){
        List<Enrollment> enrollments=getEnrollmentsForStudent(studentID);
        return enrollments.stream()
                .mapToInt(e -> e.getCourse().getCreditHours())
                .sum();
    }

    //getCountStudentsInCourse
    public int getCountStudentsInTheCourse(int courseID){
        List<Enrollment> enrollments=getEnrollmentsStudentsInTheCourse(courseID);
        return enrollments.size();
    }

    public void validateGrade(double grade){
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
    }
}
