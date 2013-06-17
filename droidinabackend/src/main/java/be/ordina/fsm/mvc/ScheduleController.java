package be.ordina.fsm.mvc;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import be.ordina.fsm.domain.Schedule;
import be.ordina.fsm.domain.Visit;
import be.ordina.fsm.service.ScheduleService;
import be.ordina.fsm.service.VisitService;

@Controller
@RequestMapping("schedules")
public class ScheduleController {

	private ScheduleService scheduleService;
	
	private VisitService visitService;
	
	@Inject
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	@Inject
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}

	@RequestMapping(value="/{id}", method=GET)
	public @ResponseBody Schedule readSchedule(@PathVariable String id){
		return scheduleService.readSchedule(id);
	}
	
	@RequestMapping(method=POST)
	public @ResponseBody Schedule createSchedule(@RequestBody Schedule schedule){
		Schedule createdSchedule = scheduleService.createSchedule(schedule);
		
		return createdSchedule;
	}
	
	@RequestMapping(method=PUT) //headers = "Content-Type=application/json"
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void putSchedule(@RequestBody Schedule schedule){
		scheduleService.updateSchedule(schedule);
	}
	
	@RequestMapping(value="/{scheduleId}/visits", method=GET)
	public @ResponseBody List<Visit> getVisitsForSchedule(@PathVariable String scheduleId){
		List<Visit> visitsForSchedule = visitService.getVisitsForSchedule(scheduleId);
		
		return visitsForSchedule;
	}
	
	@RequestMapping(method=GET)
	public @ResponseBody List<Schedule> readAllSchedules(){
		return scheduleService.readAllSchedules();
	}
}
