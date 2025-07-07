package com.my.demosms.service;

import com.my.demosms.entity.Student;
import com.my.demosms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceIMPL implements StudentService{

    @Autowired
    private StudentRepository stdRepo;


    @Override
    public Student addStudent(Student student) {
        stdRepo.save(student);
        return student;
    }

    @Override
    public List<Student> viewAllStudent() {
        return stdRepo.findAll();
    }

    @Override
    public Student getStudentByID(int id) {
        return stdRepo.findById(id).orElse(null);
    }

    @Override
    public Student updatedStudent(int id, Student student) {
        Student existStudent = stdRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found for that Id: "+ id));
        if(existStudent != null){
            existStudent.setName(student.getName());
            existStudent.setAge(student.getAge());
            existStudent.setCourse(student.getCourse());
            existStudent.setEmail(student.getEmail());
            existStudent.setAddress(student.getAddress());

           return stdRepo.save(student);
        } else {
        throw new RuntimeException("Student Not Found for This id : "+id);
            }
    }

    @Override
    public void deleteStudent(int id) {
        stdRepo.deleteById(id);
    }
}
