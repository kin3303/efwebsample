package com.demo.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.entity.Student;

@Repository
public interface AddStudentRepository extends JpaRepository<Student,Integer>{
}
