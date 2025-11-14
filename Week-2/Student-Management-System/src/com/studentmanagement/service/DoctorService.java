package com.studentmanagement.service;

import com.studentmanagement.model.Doctor;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.DoctorRepository;

import java.util.*;

public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository=doctorRepository;
    }

    public Doctor getOrCreateDoctor(String doctorName){
        Optional<Doctor> existsDoctor=doctorRepository.getDoctorByName(doctorName);
        if(existsDoctor.isPresent()){
            return existsDoctor.get();
        }else {
            return doctorRepository.addDoctor(doctorName);
        }
    }

    public Doctor updateDoctor(int doctorID, String doctorName){
        validateDoctorName(doctorName);
        getDoctorByID(doctorID);
        boolean updateSuccess=doctorRepository.updateDoctor(doctorID,doctorName);
        if(updateSuccess){
            return getDoctorByID(doctorID);
        }else{
            throw new IllegalStateException("Failed to update doctor with ID " + doctorID + " due to unexpected Repository error.");
        }
    }

    public List<Doctor> getAllDoctor(){
        return doctorRepository.getAllDoctors();
    }

    public Doctor getDoctorByID(int doctorID){
        validateDoctorID(doctorID);
        return doctorRepository.getDoctorByID(doctorID)
                .orElseThrow(() -> new NoSuchElementException(
                        "Doctor with ID " + doctorID + " cannot be found."));
    }

    public Doctor getDoctorByName(String doctorName){
        validateDoctorName(doctorName);
        return doctorRepository.getDoctorByName(doctorName)
                .orElseThrow(() -> new NoSuchElementException(
                        "Doctor with name " + doctorName + " cannot be found."));
    }

    public void validateDoctorID(int doctorID){
        if(doctorID<=0){
            throw new IllegalArgumentException("Doctor ID must be positive number");
        }
    }

    public void validateDoctorName(String doctorName){
        if (doctorName == null || doctorName.trim().length() < 3) {
            throw new IllegalArgumentException("Doctor name for search cannot be null and must be at least 3 characters long.");
        }
    }





}
