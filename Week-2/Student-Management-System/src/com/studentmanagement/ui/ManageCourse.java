package com.studentmanagement.ui;

import com.studentmanagement.model.Course;
import com.studentmanagement.model.Doctor;
import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.DoctorService;
import com.studentmanagement.service.EnrollmentService;

import java.util.List;
import java.util.NoSuchElementException;

public class ManageCourse {

    private final InputHandler inputHandler;
    private final CourseService courseService;
    private final DoctorService doctorService; // Required for linking courses to doctors
    private final EnrollmentService enrollmentService; // Required for course statistics

    public ManageCourse(InputHandler inputHandler, CourseService courseService, DoctorService doctorService, EnrollmentService enrollmentService) {
        this.inputHandler = inputHandler;
        this.courseService = courseService;
        this.doctorService = doctorService;
        this.enrollmentService = enrollmentService;
    }

    public void run() {
        System.out.println("\n--- 📚 Course Management Section ---");

        List<String> options = List.of(
                "Add New Course (or find existing by name)",
                "Display All Courses",
                "Update Course Details",
                "View Course Statistics (Student Count)",
                "Back to Main Menu"
        );
        MenuDisplay subMenu = new MenuDisplay(inputHandler, "Course Options", options);

        boolean running = true;
        while (running) {
            try {
                int choice = subMenu.showAndGetChoice();
                switch (choice) {
                    case 1 -> addOrFindCourse();
                    case 2 -> displayAllCourses();
                    case 3 -> updateCourse();
                    case 4 -> viewCourseStatistics();
                    case 5 -> running = false;
                }
            } catch (Exception e) {
                System.out.println("\n\n🚨 Course Operation Failed: " + e.getMessage());
                inputHandler.pause();
            }
        }
    }

    private void addOrFindCourse() {
        String cName = inputHandler.readNonEmpty("Enter Course Name: ");
        int hours = inputHandler.readInt("Enter Credit Hours (must be > 0): ", h -> h > 0);
        int dID = inputHandler.readInt("Enter Supervising Doctor ID: ", id -> id > 0);

        try {
            // Using getDoctorByID from DoctorService to validate/fetch the Doctor
            Doctor doctor = doctorService.getDoctorByID(dID);
            // Using getOrCreateCourse from CourseService
            Course newCourse = courseService.getOrCreateCourse(cName, hours, doctor);
            System.out.printf("\n✅ Course added/found successfully: %s (ID: %d) by Dr. %s\n",
                    newCourse.getCourseName(), newCourse.getCourseID(), newCourse.getDoctor().getDoctorName());
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println("\n❌ Operation Failed: " + e.getMessage());
        }
        inputHandler.pause();
    }

    private void displayAllCourses() {
        // Using getAllCourses from CourseService
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("--- 📭 No courses currently registered. ---");
            return;
        }
        System.out.println("\n--- 📚 Registered Courses List ---");
        System.out.println(String.format("%-5s | %-25s | %-7s | %-20s", "ID", "Name", "Credits", "Doctor"));
        System.out.println("-".repeat(60));
        courses.forEach(c ->
                System.out.println(String.format("%-5d | %-25s | %-7d | %-20s",
                        c.getCourseID(), c.getCourseName(), c.getCreditHours(), c.getDoctor().getDoctorName()))
        );
        System.out.println("------------------------------------------------------------");
        inputHandler.pause();
    }

    private void updateCourse() {
        int courseID = inputHandler.readInt("Enter Course ID to update: ", id -> id > 0);

        try {
            // Pre-validation: ensure course exists
            courseService.getCourseByID(courseID);

            String cName = inputHandler.readNonEmpty("Enter New Course Name: ");
            int hours = inputHandler.readInt("Enter New Credit Hours (must be > 0): ", h -> h > 0);
            int dID = inputHandler.readInt("Enter New Supervising Doctor ID: ", id -> id > 0);

            // Fetch Doctor
            Doctor doctor = doctorService.getDoctorByID(dID);

            // Using updateCourse from CourseService
            Course updatedCourse = courseService.updateCourse(courseID, cName, hours, doctor);
            System.out.printf("\n✅ Course updated successfully: %s (ID: %d)\n",
                    updatedCourse.getCourseName(), updatedCourse.getCourseID());
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println("\n❌ Update Failed: " + e.getMessage());
        }
        inputHandler.pause();
    }

    private void viewCourseStatistics() {
        int courseID = inputHandler.readInt("Enter Course ID for statistics: ", id -> id > 0);

        try {
            courseService.getCourseByID(courseID);
            // Using getCountStudentsInTheCourse from EnrollmentService
            int studentCount = enrollmentService.getCountStudentsInTheCourse(courseID);
            System.out.printf("\n📊 Total students currently enrolled in Course ID %d: %d\n", courseID, studentCount);
        } catch (NoSuchElementException e) {
            System.out.println("\n❌ Statistics Failed: " + e.getMessage());
        }
        inputHandler.pause();
    }

}
