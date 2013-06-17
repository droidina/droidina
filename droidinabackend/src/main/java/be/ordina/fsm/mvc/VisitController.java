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

import be.ordina.fsm.domain.Activity;
import be.ordina.fsm.domain.Visit;
import be.ordina.fsm.service.ActivityService;
import be.ordina.fsm.service.VisitService;

@Controller
@RequestMapping("visits")
public class VisitController {

	private VisitService visitService;
	private ActivityService activityService;
	
	@Inject
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}

	@Inject
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@RequestMapping(method=POST)
	public @ResponseBody Visit createVisit(@RequestBody Visit visit){
		Visit createdVisit = visitService.createVisit(visit);
		
		return createdVisit;
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public @ResponseBody Visit readVisit(@PathVariable String id){
		return visitService.readVisit(id);
	}
	
	@RequestMapping(method=GET)
	public @ResponseBody List<Visit> readAllVisits(){
		return visitService.readAllVisits();
	}
	
	@RequestMapping(method=PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void updateVisit(@RequestBody Visit visit){
		visitService.updateVisit(visit);
	}
	
	@RequestMapping(value="/{visitId}/activities", method=GET)
	public @ResponseBody List<Activity> getActivitiesForVisit(@PathVariable String visitId){
		return activityService.readActivitiesForVisit(visitId);
	}
	
}
