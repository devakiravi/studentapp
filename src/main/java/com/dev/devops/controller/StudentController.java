package com.dev.devops.controller;

import com.dev.devops.dto.StudentDTO;
import com.dev.devops.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody StudentDTO studentDTO)
    {
        return studentService.addStudent(studentDTO);
    }
}
