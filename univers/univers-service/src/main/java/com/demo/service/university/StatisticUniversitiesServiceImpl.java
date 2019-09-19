package com.demo.service.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repository.university.StatisticUniversitiesRepository;

@Service
public class StatisticUniversitiesServiceImpl implements StatisticUniversitiesService {

	@Autowired
	private StatisticUniversitiesRepository statisticUniversityRepo;
	
	public Integer getNumOfStudentsForUniversity(Integer universityId) {
		return statisticUniversityRepo.getNumOfStudentsForUniversity(universityId);
	}

}
