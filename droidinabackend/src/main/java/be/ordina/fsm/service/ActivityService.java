package be.ordina.fsm.service;

import java.util.List;

import be.ordina.fsm.domain.Activity;

public interface ActivityService {

	public Activity readActivity(String id);
	public List<Activity> readAllActivities();
	public Activity createActivity(Activity activity);
	public void updateActivity(Activity activity);
	public List<Activity> readActivitiesForVisit(String visitId);
}
