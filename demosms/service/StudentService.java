package com.my.demosms.service;

import com.my.demosms.entity.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> viewAllStudent();
    Student  getStudentByID(int id);
    Student updatedStudent(int id, Student student);
    void deleteStudent(int id);
}
