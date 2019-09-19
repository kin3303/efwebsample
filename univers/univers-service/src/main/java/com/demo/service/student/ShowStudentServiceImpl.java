package com.demo.service.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.entity.Student;
import com.demo.repository.student.ShowAllStudentsRepository;

@Service
public class ShowStudentServiceImpl implements ShowStudentService{
	
	@Autowired
	private ShowAllStudentsRepository studentRepository;
	
	public List<Student> getAllStudents() {
		return studentRepository.getAllStudents();
	}
}
