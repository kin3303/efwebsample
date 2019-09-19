package com.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="gender")
	private String gender;
	
	@ManyToOne
	@JoinColumn(name="university_id")
	private University university;
	
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Student() {
		
	}
	
	public Student(Student student) {
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.age = student.getAge();
		this.gender = student.getGender();
		this.university = student.getUniversity();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return firstName + lastName + "-" + age;
	}
}
