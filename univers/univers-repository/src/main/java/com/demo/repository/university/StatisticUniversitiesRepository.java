package com.demo.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.entity.University;
 

@Repository
public interface StatisticUniversitiesRepository extends JpaRepository<University, Integer> {
 
	@Query("select count(s) from Student s where s.university.id=:universityId")
	Integer getNumOfStudentsForUniversity(@Param("universityId") Integer universityId);
}