package com.demo.service.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.entity.University;
import com.demo.repository.university.AddUniversityRepository;

@Service
public class AddUniversityServiceImpl implements AddUniversityService {

	@Autowired
	private AddUniversityRepository addUniversityRepository;
	
	public void addUniversity(University universityDAO) {
		University university = new University(universityDAO); 
		addUniversityRepository.save(university);
	}
}
