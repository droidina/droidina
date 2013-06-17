package be.ordina.fsm.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.EntityNotFoundException;

import be.ordina.fsm.dao.ActivityDao;
import be.ordina.fsm.domain.Activity;
import be.ordina.fsm.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{

	private ActivityDao activityDao;
	
	@Inject
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	@Override
	public Activity createActivity(Activity activity) {
		Activity createdActivity = null;
		
		try {
			createdActivity = activityDao.createActivity(activity);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
		return createdActivity;
	}

	@Override
	public Activity readActivity(String id) {
		Activity activity = null;
		
		try {
			activity = activityDao.readActivity(id);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
		return activity;
	}

	@Override
	public List<Activity> readAllActivities() {
		return activityDao.readAllActivities();
	}

	@Override
	public void updateActivity(Activity activity) {
		try {
			activityDao.updateActivity(activity);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Activity> readActivitiesForVisit(String visitId) {
		return activityDao.readActivitiesForVisit(visitId);
	}
	
	
}
