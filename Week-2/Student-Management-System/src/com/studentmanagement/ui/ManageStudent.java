package com.studentmanagement.ui;

import com.studentmanagement.model.Enrollment;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.EnrollmentService;
import com.studentmanagement.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

public class ManageStudent {

    private final InputHandler inputHandler;
    private final StudentService studentService;
    private final EnrollmentService enrollmentService; // Needed for enrollment/GPA

    public ManageStudent(InputHandler inputHandler, StudentService studentService, EnrollmentService enrollmentService) {
        this.inputHandler = inputHandler;
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
    }

    public void run() {
        System.out.println("\n--- 🧑‍🎓 Student Management Section ---");

        int studentID = inputHandler.readInt("➡️ Enter Student ID (or 0 to view all students): ", id -> id >= 0);

        if (studentID == 0) {
            UIHelper.displayStudentList(studentService.getAllStudent());
            inputHandler.pause();
            return;
        }

        try {
            Student student = studentService.getStudentByID(studentID);
            System.out.printf("\n✅ Student selected: %s (ID: %d)\n", student.getStudentName(), student.getStudentID());

            displayStudentSubMenuLogic(student);

        } catch (NoSuchElementException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
            String confirm = inputHandler.readNonEmpty("Do you want to create a new student? (Y/N): ").toUpperCase();
            if (confirm.equals("Y")) {
                String newName = inputHandler.readNonEmpty("Enter the new student's name: ");
                try {
                    // Using getOrCreateStudent from StudentService
                    Student newStudent = studentService.getOrCreateStudent(newName);
                    System.out.printf("\n✅ New student created: %s (ID: %d)\n", newStudent.getStudentName(), newStudent.getStudentID());
                } catch (IllegalArgumentException ex) {
                    System.out.println("\n❌ Creation failed: " + ex.getMessage());
                }
            }
        }
        inputHandler.pause();
    }

    private void displayStudentSubMenuLogic(Student student) {
        boolean subMenuRunning = true;
        List<String> subOptions = List.of(
                "Display Detailed Student Info (GPA, Credits, Enrollments)",
                "Update Student Name",
                "Enroll in New Course",
                "Back to Main Menu"
        );
        MenuDisplay subMenu = new MenuDisplay(inputHandler,
                "Student Options for: " + student.getStudentName(),
                subOptions);

        while (subMenuRunning) {
            try {
                int choice = subMenu.showAndGetChoice();
                switch (choice) {
                    case 1 -> displayStudentDetails(student);
                    case 2 -> updateStudentName(student);
                    case 3 -> enrollStudentInNewCourse(student);
                    case 4 -> subMenuRunning = false;
                }
            } catch (Exception e) {
                System.out.println("\n\n🚨 Student Operation Failed: " + e.getMessage());
                inputHandler.pause();
            }
        }
    }

    private void displayStudentDetails(Student student) {
        System.out.println("\n--- 📄 Detailed Student Profile ---");

        System.out.println(String.format(">> ID: %d", student.getStudentID()));
        System.out.println(String.format(">> Name: %s", student.getStudentName()));

        try {
            // Using getAvgForStudent & getSumCreditHoursForStudent from EnrollmentService
            double avg = enrollmentService.getAvgForStudent(student.getStudentID());
            int totalCredits = enrollmentService.getSumCreditHoursForStudent(student.getStudentID());
            System.out.println(String.format(">> Final GPA: %.2f", avg));
            System.out.println(String.format(">> Total Graded Credit Hours: %d", totalCredits));
        } catch (NoSuchElementException e) {
            System.out.println(">> Final GPA: N/A (No graded courses found)");
        }

        // Using getEnrollmentsForStudent from EnrollmentService
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(student.getStudentID());
        // Using UIHelper to format and display the list
        UIHelper.displayEnrollments(enrollments);

        inputHandler.pause();
    }

    private void updateStudentName(Student student) {
        System.out.println("\n--- ✏️ Update Student Name ---");
        String newName = inputHandler.readNonEmpty("Enter the new name for the student: ");

        try {
            // Using updateStudent from StudentService
            Student updatedStudent = studentService.updateStudent(student.getStudentID(), newName);
            System.out.printf("\n✅ Successfully updated student name to: %s\n", updatedStudent.getStudentName());
            student.setStudentName(updatedStudent.getStudentName());
        } catch (Exception e) {
            System.out.println("\n❌ Update failed: " + e.getMessage());
        }
        inputHandler.pause();
    }

    private void enrollStudentInNewCourse(Student student) {
        System.out.println("\n--- ➕ Enroll in New Course ---");

        int courseID = inputHandler.readInt("Enter the Course ID to enroll in: ", id -> id > 0);

        try {
            // Using getOrCreateEnrollment from EnrollmentService
            Enrollment newEnrollment = enrollmentService.getOrCreateEnrollment(student.getStudentID(), courseID);
            System.out.printf("\n✅ Student %s successfully enrolled in Course ID: %d. Status: Active (N/A Grade).\n",
                    student.getStudentName(), newEnrollment.getCourse().getCourseID());
        } catch (Exception e) {
            System.out.println("\n❌ Enrollment failed: " + e.getMessage());
        }
        inputHandler.pause();
    }
}