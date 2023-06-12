package com.app.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ScheduleRepository;
import com.app.dto.ScheduleDto;
import com.app.pojos.Faculty;
import com.app.pojos.Schedule;
import com.app.pojos.Subject;

@Service
@Transactional
public class ScheduleServiceImpl implements IScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepo;

	@Override
	public void addSchedule(Schedule schedule, Faculty faculty, Subject subject) {
		schedule.setSubject(subject);
		schedule.setFaculty(faculty);
		scheduleRepo.save(schedule);
	}

	@Override
	public List<String> getSubjectNamesByDate(LocalDate date) {
		return scheduleRepo.getSubjectNamesByDate(date);
	}

	@Override
	public void updateSchedule(ScheduleDto transientSchedule) {
		Schedule schedule = scheduleRepo.findBySubjectNameAndDate(transientSchedule.getSubject(),
				transientSchedule.getDate()).orElseThrow(()->new RuntimeException("schedule not found"));
		schedule.setRecordedSessionLink(transientSchedule.getLink());
		schedule.setRecordedSessionPassword(transientSchedule.getPassword());
	}

	@Override
	public List<Schedule> findByDate(LocalDate date) {
		return scheduleRepo.findByDate(date);
	}

}
