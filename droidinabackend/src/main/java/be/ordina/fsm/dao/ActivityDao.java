package be.ordina.fsm.dao;

import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;

import be.ordina.fsm.domain.Activity;

public interface ActivityDao {
	public Activity readActivity(String id) throws EntityNotFoundException;
	public List<Activity> readAllActivities();
	public Activity createActivity(Activity activity) throws EntityNotFoundException;
	public void updateActivity(Activity activity) throws EntityNotFoundException;
	public List<Activity> readActivitiesForVisit(String visitId);
}
