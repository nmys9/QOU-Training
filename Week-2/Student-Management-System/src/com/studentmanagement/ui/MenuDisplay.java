package com.studentmanagement.ui;

import java.util.List;

public class MenuDisplay {

    private final InputHandler inputHandler;
    private final String title;
    private final List<String> options;

    public MenuDisplay(InputHandler inputHandler, String title, List<String> options) {
        this.inputHandler = inputHandler;
        this.title = title;
        this.options = options;
    }

    public int showAndGetChoice() {
        displayMenu();
        int maxChoice = options.size();

        // التحقق من أن الخيار بين 1 و عدد الخيارات
        return inputHandler.readInt(
                "\n➡️ Enter your choice (1 - " + maxChoice + "): ",
                choice -> choice >= 1 && choice <= maxChoice
        );
    }

    private void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       " + title.toUpperCase());
        System.out.println("=".repeat(50));

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.println("-".repeat(50));
    }
}