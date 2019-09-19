package com.demo.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.entity.Student;
import com.demo.repository.student.RemoveStudentRepository;

@Service
public class RemoveStudentServiceImpl implements RemoveStudentService {

	@Autowired
	private RemoveStudentRepository removeStudentRepository;
	
	public void removeStudent(Student student) {
		removeStudentRepository.delete(student);
	}

}
