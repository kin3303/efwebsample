package com.demo.repository.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.entity.Student;

@Repository
public interface RemoveStudentRepository extends JpaRepository<Student, Integer>{
	@Query("select s from Student s order by s.firstName")
	List<Student> getAllStudents();
}
