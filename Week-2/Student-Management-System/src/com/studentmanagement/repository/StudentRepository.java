package com.studentmanagement.repository;

import com.studentmanagement.model.Student;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentRepository {
    private final Map<Integer, Student> students=new HashMap<>();
    private final AtomicInteger idCounter=new AtomicInteger(1);

    public Student addStudent(String studentName){
        int id=idCounter.getAndIncrement();
        Student newStudent=new Student(id,studentName);
        students.put(id,newStudent);
        return newStudent;
    }

    public boolean updateStudent(int id,String studentName){
        Student exists=students.get(id);
        if(exists != null){
            Student updateStudent=new Student(id,studentName);
            students.put(id,updateStudent);
            return true;
        }
        return false;
    }

    public boolean removeStudent(int id){
        return students.remove(id) != null;
    }

    public List<Student> getAllStudents(){
        return students.values().stream()
                .sorted(Comparator.comparing(Student::getStudentID))
                .toList();
    }

    public Optional<Student> getStudentByID(int id){
        return Optional.ofNullable(students.get(id));
    }

    public Optional<Student> getStudentByName(String studentName) {
        return students.values().stream()
                .filter(s -> s.getStudentName().equalsIgnoreCase(studentName))
                .findFirst();
    }

    public boolean studentExists(int id){
        return students.containsKey(id);
    }


}
