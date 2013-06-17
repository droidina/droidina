package be.ordina.fsm.dao.impl;

import static com.google.appengine.api.datastore.KeyFactory.keyToString;
import static com.google.appengine.api.datastore.KeyFactory.stringToKey;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import be.ordina.fsm.dao.ActivityDao;
import be.ordina.fsm.dao.EntityMapper;
import be.ordina.fsm.domain.Activity;
import be.ordina.fsm.domain.StatusEnum;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

@Repository
public class ActivityDaoImpl implements ActivityDao, EntityMapper<Activity> {

	private static final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	@Override
	public Entity mapToEntity(Activity activity, Entity... entities) {
		Entity entity=null;
		if(entities!=null && entities.length>0){
			entity = entities[0];
		}else{
			entity = new Entity(Activity.KIND);
		}
		
		entity.setProperty(Activity.ACTIVITY_DESCRIPTION, activity.getActivityDescription());
		entity.setProperty(Activity.ACTIVITY_TYPE, activity.getActivityType());
		entity.setProperty(Activity.VISIT, stringToKey(activity.getVisitKey()));
		entity.setProperty(Activity.STATUS, activity.getStatus().toString());
		
		return entity;
	}

	@Override
	public Activity mapEntityToObject(Entity entity) {
		Activity activity = new Activity();
		
		activity.setActivityDescription((String)entity.getProperty(Activity.ACTIVITY_DESCRIPTION));
		activity.setActivityType((String)entity.getProperty(Activity.ACTIVITY_TYPE));
		activity.setKey(keyToString(entity.getKey()));
		activity.setStatus(StatusEnum.valueOf((String)entity.getProperty(Activity.STATUS)));
		activity.setVisitKey(keyToString((Key)entity.getProperty(Activity.VISIT)));
		
		return activity;
	}

	@Override
	public Activity readActivity(String id) throws EntityNotFoundException {
		Key key = stringToKey(id);
		return mapEntityToObject(ds.get(key));
	}

	@Override
	public List<Activity> readAllActivities() {
		List<Activity> allActivities = new ArrayList<Activity>();
		
		Query query = new Query(Activity.KIND);
		PreparedQuery preparedQuery = ds.prepare(query);
		
		for(Entity entity : preparedQuery.asIterable()){
			allActivities.add(mapEntityToObject(entity));
		}
		
		return allActivities;
	}

	@Override
	public Activity createActivity(Activity activity)
			throws EntityNotFoundException {
		Key key = ds.put(mapToEntity(activity));
		return mapEntityToObject(ds.get(key));
	}

	@Override
	public void updateActivity(Activity activity) throws EntityNotFoundException{
		Entity existingActivity = ds.get(stringToKey(activity.getKey()));
		Entity activityToUpdate = mapToEntity(activity, existingActivity);
		
		ds.put(existingActivity);
	}

	@Override
	public List<Activity> readActivitiesForVisit(String visitId) {
		List<Activity> foundActivities = new ArrayList<Activity>();
		
		Query query = new Query(Activity.KIND);
		query.setFilter(new Query.FilterPredicate(Activity.VISIT, FilterOperator.EQUAL, stringToKey(visitId)));
		
		PreparedQuery preparedQuery = ds.prepare(query);
		
		for(Entity entity : preparedQuery.asIterable()){
			foundActivities.add(mapEntityToObject(entity));
		}
		
		return foundActivities;
	}

}
