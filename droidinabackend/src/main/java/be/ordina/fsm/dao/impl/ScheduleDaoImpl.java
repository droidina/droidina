package be.ordina.fsm.dao.impl;

import static com.google.appengine.api.datastore.KeyFactory.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import be.ordina.fsm.dao.EntityMapper;
import be.ordina.fsm.dao.ScheduleDao;
import be.ordina.fsm.domain.Schedule;
import be.ordina.fsm.domain.Visit;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

@Repository
public class ScheduleDaoImpl implements ScheduleDao, EntityMapper<Schedule> {

	private static final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	private static final Logger logger = Logger.getLogger(ScheduleDaoImpl.class.getName());
	
	@Override
	public Entity mapToEntity(Schedule schedule, Entity... entities) {
		Entity entity = null;
		if(entities!=null && entities.length>0){
			entity = entities[0];
		}else{
			entity = new Entity(Schedule.KIND);
		}
		
		entity.setProperty(Schedule.FROM_DATE, schedule.getFromDate());
		entity.setProperty(Schedule.TO_DATE, schedule.getToDate());
		entity.setProperty(Schedule.SCHEDULE_DESCRIPTION, schedule.getScheduleDescription());
		entity.setProperty(Schedule.AGENT, stringToKey(schedule.getAgentEntityKey()));
		
		if(schedule.getVisitEntityKeys()!=null && !schedule.getVisitEntityKeys().isEmpty()){
			entity.setProperty(Schedule.VISITS, schedule.getVisitEntityKeys());
		}
		
		return entity;
	}

	@Override
	public Schedule mapEntityToObject(Entity entity) {
		Schedule schedule = new Schedule();
		
		schedule.setAgentEntityKey(keyToString((Key)entity.getProperty(Schedule.AGENT)));
		schedule.setFromDate((Date)entity.getProperty(Schedule.FROM_DATE));
		schedule.setToDate((Date)entity.getProperty(Schedule.TO_DATE));
		schedule.setScheduleDescription((String)entity.getProperty(Schedule.SCHEDULE_DESCRIPTION));
		schedule.setKey(keyToString(entity.getKey()));
		
		return schedule;
	}

	@Override
	public Schedule readSchedule(String id) throws EntityNotFoundException{
		Key key = stringToKey(id);
		
		return mapEntityToObject(ds.get(key));
	}
	
	@Override
	public Schedule createSchedule(Schedule schedule) throws EntityNotFoundException{
		Entity entity = mapToEntity(schedule);
		
		Key key = ds.put(entity);
		
		return mapEntityToObject(ds.get(key));
	}

	@Override
	public void updateSchedule(Schedule schedule) throws EntityNotFoundException{
		Key scheduleKey = stringToKey(schedule.getKey());
		Entity foundSchedule = ds.get(scheduleKey);
		
		Entity scheduleToUpdate = mapToEntity(schedule, foundSchedule);
		ds.put(scheduleToUpdate);
	}

	@Override
	public List<Schedule> findScheduleByAgentId(String id) {
		List<Schedule> schedulesForAgent = new ArrayList<Schedule>();
		
		Query query = new Query(Schedule.KIND);
		query.setFilter(new Query.FilterPredicate(Schedule.AGENT, FilterOperator.EQUAL, stringToKey(id)));
		
		PreparedQuery preparedQuery = ds.prepare(query);
		for(Entity entity:preparedQuery.asIterable()){
			schedulesForAgent.add(mapEntityToObject(entity));
		}
		
		return schedulesForAgent;
	}

	@Override
	public List<Schedule> readAllSchedules() {	
		List<Schedule> allSchedules = new ArrayList<Schedule>();
		Query query = new Query(Schedule.KIND);
		
		PreparedQuery preparedQuery = ds.prepare(query);
		for(Entity entity : preparedQuery.asIterable()){
			allSchedules.add(mapEntityToObject(entity));
		}
		return allSchedules;
	}
}
