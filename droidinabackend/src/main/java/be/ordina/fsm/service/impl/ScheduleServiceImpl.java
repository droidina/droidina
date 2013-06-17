package be.ordina.fsm.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import be.ordina.fsm.dao.ScheduleDao;
import be.ordina.fsm.domain.Schedule;
import be.ordina.fsm.service.ScheduleService;

import com.google.appengine.api.datastore.EntityNotFoundException;

@Service
public class ScheduleServiceImpl implements ScheduleService{

	private ScheduleDao scheduleDao;

	@Inject
	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	@Override
	public Schedule readSchedule(String id) {
		Schedule foundSchedule = null;
		try {
			foundSchedule = scheduleDao.readSchedule(id);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return foundSchedule;
	}
	
	@Override
	public Schedule createSchedule(Schedule schedule) {
		Schedule foundSchedule = null;
		try {
			foundSchedule = scheduleDao.createSchedule(schedule);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return foundSchedule;
	}

	@Override
	public void updateSchedule(Schedule schedule) {
		try {
			scheduleDao.updateSchedule(schedule);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Schedule> findScheduleByAgentId(String agentId) {
		List<Schedule> schedules = scheduleDao.findScheduleByAgentId(agentId);
		
		return schedules;
	}
	
	@Override
	public List<Schedule> readAllSchedules(){
		return scheduleDao.readAllSchedules();
	}
}
