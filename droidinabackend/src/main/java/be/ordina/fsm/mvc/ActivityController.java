package be.ordina.fsm.mvc;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import be.ordina.fsm.domain.Activity;
import be.ordina.fsm.service.ActivityService;

@Controller
@RequestMapping(value="/activities")
public class ActivityController {

	private ActivityService activityService;
	
	@Inject
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@RequestMapping(method=POST)
	public @ResponseBody Activity createActivity(@RequestBody Activity activity){
		return activityService.createActivity(activity);
	}
	
	@RequestMapping(method=PUT)
	public void updateActivity(@RequestBody Activity activity){
		activityService.updateActivity(activity);
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public @ResponseBody Activity readActivity(@PathVariable String id){
		return activityService.readActivity(id);
	}

	@RequestMapping(method=GET)
	public @ResponseBody List<Activity> readAllActivities(){
		return activityService.readAllActivities();
	}
	
}
