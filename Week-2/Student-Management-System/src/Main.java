import com.studentmanagement.ui.StudentManagementApp;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StudentManagementApp app = new StudentManagementApp(scanner);
            app.run();
        }
       
    }
}