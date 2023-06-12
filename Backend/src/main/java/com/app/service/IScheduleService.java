package com.app.service;

import java.time.LocalDate;
import java.util.List;

import com.app.dto.ScheduleDto;
import com.app.pojos.Faculty;
import com.app.pojos.Schedule;
import com.app.pojos.Subject;

public interface IScheduleService {
   public void addSchedule(Schedule schedule,Faculty faculty,Subject subject);
   public List<String> getSubjectNamesByDate(LocalDate date) ;
   public void updateSchedule(ScheduleDto transientSchedule);

   public List<Schedule> findByDate(LocalDate date);
}
