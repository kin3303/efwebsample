package com.demo.service.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.entity.Student;
import com.demo.repository.student.AddStudentRepository;

@Service
public class AddStudentServiceImpl implements AddStudentService {

	@Autowired
	private AddStudentRepository studentRepository;

	public void saveStudent(Student studentDAO) {
		Student student = new Student(studentDAO);
		studentRepository.save(student);
	}
}
