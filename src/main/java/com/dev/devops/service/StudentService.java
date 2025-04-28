package com.dev.devops.service;

import com.dev.devops.dto.StudentDTO;
import com.dev.devops.entity.StudentEntity;
import com.dev.devops.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    public ResponseEntity<Void> addStudent(StudentDTO studentDTO)
    {
        StudentEntity studentEntity = StudentEntity.builder()
                .name(studentDTO.getName())
                .department(studentDTO.getName())
                .section(studentDTO.getSection())
                .year(studentDTO.getYear())
                .joinedAt(LocalDateTime.now().toString())
                .build();

        studentRepo.save(studentEntity);
        return ResponseEntity.ok().build();
    }

}
