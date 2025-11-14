package com.studentmanagement.ui;

import com.studentmanagement.model.Doctor;
import com.studentmanagement.service.DoctorService;

import java.util.List;
import java.util.NoSuchElementException;

public class ManageDoctor {

    private final InputHandler inputHandler;
    private final DoctorService doctorService;

    public ManageDoctor(InputHandler inputHandler, DoctorService doctorService) {
        this.inputHandler = inputHandler;
        this.doctorService = doctorService;
    }

    public void run() {
        System.out.println("\n--- 👨‍🏫 Doctor Management Section ---");

        List<String> options = List.of(
                "Add New Doctor (or find existing by name)",
                "Display All Doctors",
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
                    case 3 -> updateDoctor();
                    case 4 -> running = false;
                }
            } catch (Exception e) {
                System.out.println("\n\n🚨 Doctor Operation Failed: " + e.getMessage());
                inputHandler.pause();
            }
        }
    }

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
