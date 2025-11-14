package com.studentmanagement.ui;

import com.studentmanagement.model.*; // يفترض وجود كلاسات Model هنا

import java.util.List;

public class UIHelper {

    // 💡 لعرض قائمة الطلاب
    public static void displayStudentList(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("--- 📭 No students currently registered. ---");
            return;
        }
        System.out.println("\n--- 🧑‍🎓 Registered Students List ---");
        System.out.println(String.format("%-5s | %-30s", "ID", "Name"));
        System.out.println("-".repeat(37));
        students.forEach(s ->
                System.out.println(String.format("%-5d | %-30s", s.getStudentID(), s.getStudentName())) // يفترض getStudentName
        );
        System.out.println("------------------------------------");
    }

    // 💡 لعرض قائمة التسجيلات
    public static void displayEnrollments(List<Enrollment> enrollments) {
        if (enrollments.isEmpty()) {
            System.out.println("--- 📭 No enrollment records found. ---");
            return;
        }
        System.out.println("\n--- 📝 Enrollment Records ---");
        System.out.println(String.format("%-5s | %-10s | %-20s | %-5s | %-7s", "EID", "StudentID", "Course Name", "Credits", "Grade"));
        System.out.println("-".repeat(60));
        enrollments.forEach(e -> {
            String grade = (e.getGrade() != null) ? String.format("%.2f", e.getGrade()) : "N/A";
            System.out.println(String.format("%-5d | %-10d | %-20s | %-7d | %-7s",
                    e.getEnrolID(),
                    e.getStudent().getStudentID(),
                    e.getCourse().getCourseName(),
                    e.getCourse().getCreditHours(),
                    grade));
        });
        System.out.println("------------------------------------------------------------");
    }
}