package com.demo.service.university;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.entity.University;
import com.demo.repository.university.ShowAllUniversitiesRepository;

@Service
public class ShowAllUniversitiesServiceImpl implements ShowAllUniversitiesService {

	@Autowired
	private ShowAllUniversitiesRepository showAllUniversitiesRepository;
	
	public List<University> getAllUniversities() {
		return showAllUniversitiesRepository.getAllUniversities();
	}
}
