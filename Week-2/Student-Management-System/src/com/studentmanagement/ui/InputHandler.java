package com.studentmanagement.ui;

import java.util.Scanner;
import java.util.function.Predicate;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt(String prompt) {
        return readInt(prompt, null);
    }

    public int readInt(String prompt, Predicate<Integer> validator) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());

                if (validator == null || validator.test(value)) {
                    return value;
                } else {
                    System.out.println("❌ Invalid value! Please check the range or condition.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid whole number.");
            }
        }
    }

    public double readDouble(String prompt, Predicate<Double> validator) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());

                if (validator == null || validator.test(value)) {
                    return value;
                } else {
                    System.out.println("❌ Invalid value! Please check the range or condition.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid decimal number.");
            }
        }
    }

    public String readString(String prompt, Predicate<String> validator) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (validator == null || validator.test(input)) {
                return input;
            } else {
                System.out.println("❌ Invalid input! Please meet the required format/length.");
            }
        }
    }

    public String readNonEmpty(String prompt) {
        return readString(prompt, input -> !input.isEmpty());
    }

    public void pause() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}