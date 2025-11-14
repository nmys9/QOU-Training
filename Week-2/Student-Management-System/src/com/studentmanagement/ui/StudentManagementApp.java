package com.studentmanagement.ui;

import com.studentmanagement.service.*;
import com.studentmanagement.repository.*;
import com.studentmanagement.model.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentManagementApp {

    private final InputHandler inputHandler;

    // Services
    private final StudentService studentService;
    private final CourseService courseService;
    private final DoctorService doctorService;
    private final EnrollmentService enrollmentService;

    // UI Controllers
    private final ManageStudent manageStudent;
    private final ManageDoctor manageDoctor;
    private final ManageCourse manageCourse;

    // Seed Data variables
    private Student s1, s2;
    private Doctor d1, d2;
    private Course c1, c2, c3;

    public StudentManagementApp(Scanner scanner) {
        this.inputHandler = new InputHandler(scanner);

        // 1. Initialize Repositories (Assuming they are written correctly)
        StudentRepository studentRepository = new StudentRepository();
        DoctorRepository doctorRepository = new DoctorRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        // 2. Initialize Services
        this.studentService = new StudentService(studentRepository);
        this.doctorService = new DoctorService(doctorRepository);
        // CourseService depends on DoctorService
        this.courseService = new CourseService(courseRepository, this.doctorService);
        // EnrollmentService depends on StudentService and CourseService
        this.enrollmentService = new EnrollmentService(enrollmentRepository, this.studentService, this.courseService);

        // 3. Initialize UI Controllers (Dependency Injection)
        this.manageStudent = new ManageStudent(inputHandler, studentService, enrollmentService);
        this.manageDoctor = new ManageDoctor(inputHandler, doctorService);
        this.manageCourse = new ManageCourse(inputHandler, courseService, doctorService, enrollmentService);

        // 4. Seed initial data
        seedInitialData();
    }

    private void seedInitialData() {
        System.out.println("... ⚙️ Initializing seed data...");

        d1 = doctorService.getOrCreateDoctor("Dr. Ahmad Ali");
        d2 = doctorService.getOrCreateDoctor("Dr. Sara Omar");

        c1 = courseService.getOrCreateCourse("Programming 1", 3, d1);
        c2 = courseService.getOrCreateCourse("Calculus 2", 3, d2);
        c3 = courseService.getOrCreateCourse("Data Structures", 4, d1);

        s1 = studentService.getOrCreateStudent("Zaid Emad");
        s2 = studentService.getOrCreateStudent("Lina Tariq");

        // Seed enrollments and grades
        enrollmentService.updateGrade(s1.getStudentID(), c1.getCourseID(), 85.0);
        enrollmentService.updateGrade(s1.getStudentID(), c2.getCourseID(), 90.0);
        enrollmentService.getOrCreateEnrollment(s1.getStudentID(), c3.getCourseID());

        enrollmentService.updateGrade(s2.getStudentID(), c1.getCourseID(), 75.5);
        enrollmentService.getOrCreateEnrollment(s2.getStudentID(), c3.getCourseID());

        System.out.println("... ✅ Data loaded successfully (2 Students, 2 Doctors, 3 Courses).");
    }

    public void run() {
        boolean running = true;
        List<String> options = List.of(
                "Manage Students (View/Update/Enroll)",
                "Manage Doctors",
                "Manage Courses",
                "Update Course Grades",
                "View Student GPA",
                "Exit"
        );
        MenuDisplay mainMenu = new MenuDisplay(inputHandler, "Student Management System - Main Menu", options);

        while (running) {
            try {
                int choice = mainMenu.showAndGetChoice();
                switch (choice) {
                    case 1 -> manageStudent.run(); // Delegate to ManageStudent
                    case 2 -> manageDoctor.run();  // Delegate to ManageDoctor
                    case 3 -> manageCourse.run();  // Delegate to ManageCourse
                    case 4 -> handleGradeUpdateMenu();
                    case 5 -> handleStudentGPAView();
                    case 6 -> { running = false; System.out.println("\n👋 Thank you for using the system. Goodbye!"); }
                }
            } catch (Exception e) {
                System.out.println("\n\n🚨 CRITICAL APPLICATION ERROR: " + e.getMessage());
                inputHandler.pause();
            }
        }
    }

    // Menu handlers that don't belong specifically to Student/Course/Doctor management
    // Example: Grade Update (involves student/course/enrollment logic but is a simple transactional operation)

    private void handleGradeUpdateMenu() {
        System.out.println("\n--- 📝 Update Student Grade ---");

        int studentID = inputHandler.readInt("Enter Student ID: ", id -> id > 0);
        int courseID = inputHandler.readInt("Enter Course ID: ", id -> id > 0);
        double grade = inputHandler.readDouble("Enter new Grade (0-100): ", g -> g >= 0 && g <= 100);

        try {
            // Using updateGrade from EnrollmentService
            Enrollment updated = enrollmentService.updateGrade(studentID, courseID, grade);
            System.out.println("\n✅ Grade updated/assigned successfully!");
            System.out.printf("   New Grade for Enrollment ID %d: %.2f\n", updated.getEnrolID(), updated.getGrade());
        } catch (NoSuchElementException e) {
            System.out.println("\n❌ Operation failed: " + e.getMessage());
        }
        inputHandler.pause();
    }

    private void handleStudentGPAView() {
        System.out.println("\n--- 📊 Student GPA Calculation ---");
        int studentID = inputHandler.readInt("Enter Student ID to calculate GPA: ", id -> id > 0);

        try {
            // Using getAvgForStudent from EnrollmentService
            double avg = enrollmentService.getAvgForStudent(studentID);
            System.out.printf("\n📊 Student ID %d Final Average (GPA): %.2f\n", studentID, avg);
        } catch (NoSuchElementException e) {
            System.out.println("\n❌ Cannot calculate average: " + e.getMessage());
        }
        inputHandler.pause();
    }
}