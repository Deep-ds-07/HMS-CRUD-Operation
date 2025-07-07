package com.my.demosms.controller;

import com.my.demosms.entity.Student;
import com.my.demosms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/std")
public class StudentController {

    @Autowired
    private StudentService stdService;

    @PostMapping
    public Student addStd (@RequestBody Student student){
        return stdService.addStudent(student);
    }
    @GetMapping
    public List<Student> getAllStudent(){
        return stdService.viewAllStudent();
    }

   @GetMapping("/{id}")
public ResponseEntity<Student> getStdById(@PathVariable int id) {
    Student student = stdService.getStudentByID(id);
    if (student != null) {
        return ResponseEntity.ok(student);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @PutMapping("/{id}")
    public ResponseEntity <Student> updateStd(@PathVariable int id, @RequestBody Student student){
        try {
             return ResponseEntity.ok(stdService.updatedStudent(id, student));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStd (@PathVariable int id ){
        stdService.deleteStudent(id);
        return ResponseEntity.ok("Student Deleted Successfully");
    }


}
