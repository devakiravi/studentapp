package com.dev.devops.repository;

import com.dev.devops.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends MongoRepository<StudentEntity,String> {
}
