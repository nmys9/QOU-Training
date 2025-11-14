package com.studentmanagement.repository;

import com.studentmanagement.model.Doctor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DoctorRepository {
    private final Map<Integer, Doctor> doctors=new HashMap<>();
    private final AtomicInteger idCounter=new AtomicInteger(1);

    public Doctor addDoctor(String doctorName){
        int id=idCounter.getAndIncrement();
        Doctor newDoctor=new Doctor(id,doctorName);
        doctors.put(id,newDoctor);
        return newDoctor;
    }

    public boolean updateDoctor(int id,String doctorName){
        Doctor exists=doctors.get(id);
        if(exists != null){
            Doctor updateDoctor=new Doctor(id,doctorName);
            doctors.put(id,updateDoctor);
            return true;
        }
        return false;
    }

    public boolean removeDoctor(int id){
        return doctors.remove(id) != null;
    }

    public List<Doctor> getAllDoctors(){
        return doctors.values().stream()
                .sorted(Comparator.comparing(Doctor::getDoctorID))
                .toList();
    }

    public Optional<Doctor> getDoctorByID(int id){
        return Optional.ofNullable(doctors.get(id));
    }

    public Optional<Doctor> getDoctorByName(String doctorName){
        return doctors.values().stream()
                .filter(d -> d.getDoctorName().equalsIgnoreCase(doctorName))
                .findFirst();

    }
    public boolean doctorExistse(int id){
        return doctors.containsKey(id);
    }

}
