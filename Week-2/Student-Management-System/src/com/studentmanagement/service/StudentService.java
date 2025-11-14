package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public Student getOrCreateStudent(String studentName){
        Optional<Student> existsStudent=studentRepository.getStudentByName(studentName);

        if(existsStudent.isPresent()){
            return existsStudent.get();
        }else {
            Student student= studentRepository.addStudent(studentName);
            return student;
        }
    }

    public Student updateStudent(int studentID,String studentName){
        validateStudentName(studentName);
        getStudentByID(studentID);
        boolean updateSuccess=studentRepository.updateStudent(studentID,studentName);

        if(updateSuccess){
            return getStudentByID(studentID);
        }else{
            throw new IllegalStateException("Failed to update student with ID " + studentID + " due to unexpected Repository error.");
        }
    }

    public List<Student> getAllStudent(){
        return studentRepository.getAllStudents();
    }

    public Student getStudentByID(int studentID){
        validateStudentID(studentID);
        return studentRepository.getStudentByID(studentID).
                orElseThrow(()-> new NoSuchElementException(
                        "Student with ID "+studentID+" cannot be found")
                );
    }

    public Student getStudentByName(String studentName){
        validateStudentName(studentName);
        return studentRepository.getStudentByName(studentName)
                .orElseThrow(()-> new NoSuchElementException(
                        "Student with name "+ studentName +" cannot be found."));
    }

    public void validateStudentID(int studentID){
        if(studentID<=0){
            throw new IllegalArgumentException("Student ID must be positive number");
        }
    }
    public void validateStudentName(String studentName){
        if (studentName == null || studentName.trim().length() < 3) {
            throw new IllegalArgumentException("Student name for search cannot be null and must be at least 3 characters long.");
        }
    }


}
