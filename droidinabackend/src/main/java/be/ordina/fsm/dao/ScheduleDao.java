package be.ordina.fsm.dao;

import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;

import be.ordina.fsm.domain.Schedule;

public interface ScheduleDao {

	public Schedule readSchedule(String id) throws EntityNotFoundException;
	public Schedule createSchedule(Schedule schedule) throws EntityNotFoundException;
	public void updateSchedule(Schedule schedule) throws EntityNotFoundException;
	public List<Schedule> findScheduleByAgentId(String id);
	public List<Schedule> readAllSchedules();
}
