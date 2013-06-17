package be.ordina.fsm.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import be.ordina.fsm.domain.Schedule;
import be.ordina.fsm.domain.Schedule;

public interface ScheduleService {

	public Schedule readSchedule(String id);
	public List<Schedule> readAllSchedules();
	public Schedule createSchedule(Schedule schedule);
	public void updateSchedule(Schedule schedule);
	public List<Schedule> findScheduleByAgentId(String agentId);
}
