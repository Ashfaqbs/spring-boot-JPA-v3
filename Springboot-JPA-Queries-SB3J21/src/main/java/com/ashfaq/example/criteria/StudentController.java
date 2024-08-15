package com.ashfaq.example.criteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/filter")
    public List<Student> getFilteredStudents(@RequestBody StudentFilterRequest request) {
        return studentService.getStudentsByCriteria(request.getIds(), request.getNames(), request.getDepartments());
    }
    
    
    @GetMapping("/filterv2")
    public List<Student> getFilteredStudentsv2(@RequestBody StudentFilterRequest filterRequest) {
        return studentService.getStudentsByCriteriav2(filterRequest);
    }
}

