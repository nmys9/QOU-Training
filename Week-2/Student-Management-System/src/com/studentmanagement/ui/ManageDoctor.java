//package com.studentmanagement.ui;
//
//import com.studentmanagement.model.Doctor;
//import com.studentmanagement.service.DoctorService;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//public class ManageDoctor {
//
//    private final InputHandler inputHandler;
//    private final DoctorService doctorService;
//
//    public ManageDoctor(InputHandler inputHandler, DoctorService doctorService) {
//        this.inputHandler = inputHandler;
//        this.doctorService = doctorService;
//    }
//
//    public void run() {
//        System.out.println("\n--- 👨‍🏫 Doctor Management Section ---");
//
//        List<String> options = List.of(
//                "Add New Doctor (or find existing by name)",
//                "Display All Doctors",
//                "Update Doctor Name by ID",
//                "Back to Main Menu"
//        );
//        MenuDisplay subMenu = new MenuDisplay(inputHandler, "Doctor Options", options);
//
//        boolean running = true;
//        while (running) {
//            try {
//                int choice = subMenu.showAndGetChoice();
//                switch (choice) {
//                    case 1 -> addOrFindDoctor();
//                    case 2 -> displayAllDoctors();
//                    case 3 -> updateDoctor();
//                    case 4 -> running = false;
//                }
//            } catch (Exception e) {
//                System.out.println("\n\n🚨 Doctor Operation Failed: " + e.getMessage());
//                inputHandler.pause();
//            }
//        }
//    }
//
//    private void addOrFindDoctor() {
//        String name = inputHandler.readNonEmpty("Enter Doctor Name: ");
//        try {
//            // Using getOrCreateDoctor from DoctorService
//            Doctor doctor = doctorService.getOrCreateDoctor(name);
//            System.out.printf("\n✅ Success! Doctor found/created: %s (ID: %d)\n",
//                    doctor.getDoctorName(), doctor.getDoctorID());
//        } catch (IllegalArgumentException e) {
//            System.out.println("\n❌ Error: " + e.getMessage());
//        }
//        inputHandler.pause();
//    }
//
//    private void displayAllDoctors() {
//        // Using getAllDoctor from DoctorService
//        List<Doctor> doctors = doctorService.getAllDoctor();
//        if (doctors.isEmpty()) {
//            System.out.println("--- 📭 No doctors currently registered. ---");
//            return;
//        }
//        System.out.println("\n--- 👨‍🏫 Registered Doctors List ---");
//        System.out.println(String.format("%-5s | %-30s", "ID", "Name"));
//        System.out.println("-".repeat(37));
//        doctors.forEach(d ->
//                System.out.println(String.format("%-5d | %-30s", d.getDoctorID(), d.getDoctorName()))
//        );
//        System.out.println("------------------------------------");
//        inputHandler.pause();
//    }
//
//    private void updateDoctor() {
//        int doctorID = inputHandler.readInt("Enter Doctor ID to update: ", id -> id > 0);
//        String newName = inputHandler.readNonEmpty("Enter the new name for the doctor: ");
//
//        try {
//            // Using updateDoctor from DoctorService
//            Doctor updatedDoctor = doctorService.updateDoctor(doctorID, newName);
//            System.out.printf("\n✅ Successfully updated doctor name to: %s (ID: %d)\n",
//                    updatedDoctor.getDoctorName(), updatedDoctor.getDoctorID());
//        } catch (NoSuchElementException | IllegalStateException e) {
//            System.out.println("\n❌ Update failed: " + e.getMessage());
//        }
//        inputHandler.pause();
//    }
//
//}


package com.studentmanagement.ui;

import com.studentmanagement.model.Course; // يجب استيراد كلاس Course
import com.studentmanagement.model.Doctor;
import com.studentmanagement.service.DoctorService;
import com.studentmanagement.service.CourseService; // يجب استيراد كلاس CourseService

import java.util.List;
import java.util.NoSuchElementException;

public class ManageDoctor {

    private final InputHandler inputHandler;
    private final DoctorService doctorService;
    private final CourseService courseService; // إضافة CourseService

    // تحديث الدالة البانية (Constructor) لقبول CourseService
    public ManageDoctor(InputHandler inputHandler, DoctorService doctorService,CourseService courseService) {
        this.inputHandler = inputHandler;
        this.doctorService = doctorService;
        this.courseService = courseService; // تعيين الكلاس
    }

    public void run() {
        System.out.println("\n--- 👨‍🏫 Doctor Management Section ---");

        // تحديث قائمة الخيارات (تمت إضافة الخيار الثالث)
        List<String> options = List.of(
                "Add New Doctor (or find existing by name)",
                "Display All Doctors",
                "Display Courses Taught by Doctor ID", // الخيار الجديد (3)
                "Update Doctor Name by ID",
                "Back to Main Menu"
        );
        MenuDisplay subMenu = new MenuDisplay(inputHandler, "Doctor Options", options);

        boolean running = true;
        while (running) {
            try {
                int choice = subMenu.showAndGetChoice();
                switch (choice) {
                    case 1 -> addOrFindDoctor();
                    case 2 -> displayAllDoctors();
                    case 3 -> displayDoctorCourses(); // استدعاء الدالة الجديدة
                    case 4 -> updateDoctor();
                    case 5 -> running = false; // "العودة للقائمة الرئيسية" أصبحت الخيار 5
                }
            } catch (Exception e) {
                System.out.println("\n\n🚨 Doctor Operation Failed: " + e.getMessage());
                inputHandler.pause();
            }
        }
    }

    // *** الدالة الجديدة لعرض كورسات الدكتور ***
    private void displayDoctorCourses() {
        System.out.println("\n--- 🔎 Display Doctor Courses ---");
        int doctorID = inputHandler.readInt("Enter Doctor ID to view their courses: ", id -> id > 0);

        try {
            // استدعاء دالة الخدمة الجديدة
            List<Course> courses = courseService.getCoursesByDoctorID(doctorID);

            if (courses.isEmpty()) {
                // ستصل هنا إذا كان معرّف الدكتور صحيحاً ولكنه لا يدرس أي مساقات
                System.out.printf("\n--- 📭 Doctor ID %d exists, but is not currently teaching any courses. ---\n", doctorID);
                return;
            }

            System.out.printf("\n--- 📚 Courses Taught by Doctor ID %d ---\n", doctorID);
            System.out.println(String.format("%-5s | %-30s | %-10s", "ID", "Course Name", "Credits"));
            System.out.println("-".repeat(50));
            // عرض قائمة المساقات
            courses.forEach(c ->
                    System.out.println(String.format("%-5d | %-30s | %-10d",
                            c.getCourseID(), c.getCourseName(), c.getCreditHours()))
            );
            System.out.println("--------------------------------------------------");
        } catch (NoSuchElementException e) {
            // التقاط الاستثناء في حال عدم العثور على الدكتور (يتم رميه من داخل getCoursesByDoctorID)
            System.out.println("\n❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n\n🚨 Operation Failed: " + e.getMessage());
        }
        inputHandler.pause();
    }

    // الدوال الأخرى (addOrFindDoctor و displayAllDoctors و updateDoctor) تبقى كما هي
    private void addOrFindDoctor() {
        String name = inputHandler.readNonEmpty("Enter Doctor Name: ");
        try {
            // Using getOrCreateDoctor from DoctorService
            Doctor doctor = doctorService.getOrCreateDoctor(name);
            System.out.printf("\n✅ Success! Doctor found/created: %s (ID: %d)\n",
                    doctor.getDoctorName(), doctor.getDoctorID());
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
        inputHandler.pause();
    }

    private void displayAllDoctors() {
        // Using getAllDoctor from DoctorService
        List<Doctor> doctors = doctorService.getAllDoctor();
        if (doctors.isEmpty()) {
            System.out.println("--- 📭 No doctors currently registered. ---");
            return;
        }
        System.out.println("\n--- 👨‍🏫 Registered Doctors List ---");
        System.out.println(String.format("%-5s | %-30s", "ID", "Name"));
        System.out.println("-".repeat(37));
        doctors.forEach(d ->
                System.out.println(String.format("%-5d | %-30s", d.getDoctorID(), d.getDoctorName()))
        );
        System.out.println("------------------------------------");
        inputHandler.pause();
    }

    private void updateDoctor() {
        int doctorID = inputHandler.readInt("Enter Doctor ID to update: ", id -> id > 0);
        String newName = inputHandler.readNonEmpty("Enter the new name for the doctor: ");

        try {
            // Using updateDoctor from DoctorService
            Doctor updatedDoctor = doctorService.updateDoctor(doctorID, newName);
            System.out.printf("\n✅ Successfully updated doctor name to: %s (ID: %d)\n",
                    updatedDoctor.getDoctorName(), updatedDoctor.getDoctorID());
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("\n❌ Update failed: " + e.getMessage());
        }
        inputHandler.pause();
    }

}