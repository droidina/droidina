package be.ordina.fsm.mvc;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import be.ordina.fsm.domain.Agent;
import be.ordina.fsm.domain.Schedule;
import be.ordina.fsm.service.AgentService;
import be.ordina.fsm.service.ScheduleService;

@Controller
@RequestMapping("/agents")
public class AgentController {
	
	private AgentService agentService;
	private ScheduleService scheduleService;

	@Inject
	public void setService(AgentService agentService) {
		this.agentService = agentService;
	}	

	@Inject
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@RequestMapping(method=GET)
	public @ResponseBody List<Agent> readAgents(){
		List<Agent> agents = agentService.readAllAgents();
		
		return agents;
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public @ResponseBody Agent readAgent(@PathVariable String id){		
		Agent agent = agentService.readAgent(id);
		
		return agent;
	}
	
	@RequestMapping(value="/{id}/schedules", method=GET)
	public @ResponseBody List<Schedule> getSchedulesForAgent(@PathVariable String id){
		List<Schedule> schedules = scheduleService.findScheduleByAgentId(id);
		
		return schedules;
	}
	
	@RequestMapping(method=POST)
	public @ResponseBody Agent createAgent(@RequestBody Agent agent){
		Agent createdAgent = agentService.createAgent(agent);
		
		return createdAgent;
	}
	
	@RequestMapping(value="/login", method=POST)
	public @ResponseBody Agent login(@RequestBody Agent agent){
		Agent loggedOnAgent = agentService.login(agent.getUserName(), agent.getPassword());
		
		return loggedOnAgent;
	}
	
	@RequestMapping(method=PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void updateAgent(@RequestBody Agent agent){
		agentService.updateAgent(agent);
	}
}
