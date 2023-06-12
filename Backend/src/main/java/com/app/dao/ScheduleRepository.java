package com.app.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
	@Query(value = "select subject_name from schedule where date =?1", nativeQuery = true)
	public List<String> getSubjectNamesByDate(LocalDate date);
	
	public Optional<Schedule>findBySubjectNameAndDate(String subjectName,LocalDate date);

	public List<Schedule> findByDate(LocalDate date);
	
}
