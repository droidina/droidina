package be.ordina.fsm.dao.impl;

import static com.google.appengine.api.datastore.KeyFactory.keyToString;
import static com.google.appengine.api.datastore.KeyFactory.stringToKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import be.ordina.fsm.dao.EntityMapper;
import be.ordina.fsm.dao.VisitDao;
import be.ordina.fsm.domain.StatusEnum;
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
public class VisitDaoImpl implements VisitDao, EntityMapper<Visit>{
	
	private static final DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	@Override
	public Entity mapToEntity(Visit visit, Entity... entities) {
		Entity entity = null;
		if(entities!=null && entities.length>0){
			entity = entities[0];
		}else{
			entity = new Entity(Visit.KIND);
		}
		
		entity.setProperty(Visit.ADDRESS_BOX, visit.getAddressBox());
		entity.setProperty(Visit.ADDRESS_CITY, visit.getAddressCity());
		entity.setProperty(Visit.ADDRESS_STREET, visit.getAddressStreet());
		entity.setProperty(Visit.ADDRESS_STREET_NUMBER, visit.getAddressStreetNumber());
		entity.setProperty(Visit.ADDRESS_ZIP_CODE, visit.getAddressZipCode());
		entity.setProperty(Visit.FROM_DATE, visit.getFromDate());
		entity.setProperty(Visit.STATUS, visit.getStatus().toString());
		entity.setProperty(Visit.TO_DATE, visit.getToDate());
		entity.setProperty(Visit.VISIT_DESCRIPTION, visit.getVisitDescription());
		entity.setProperty(Visit.SCHEDULE_KEY, stringToKey(visit.getScheduleKey()));
		
		return entity;
	}

	@Override
	public Visit mapEntityToObject(Entity entity) {
		Visit visit = new Visit();
		
		visit.setKey(keyToString(entity.getKey()));
		visit.setScheduleKey(keyToString((Key)entity.getProperty(Visit.SCHEDULE_KEY)));
		
		visit.setAddressBox((String)entity.getProperty(Visit.ADDRESS_BOX));
		visit.setAddressCity((String)entity.getProperty(Visit.ADDRESS_CITY));
		visit.setAddressStreet((String)entity.getProperty(Visit.ADDRESS_STREET));
		visit.setAddressStreetNumber((String)entity.getProperty(Visit.ADDRESS_STREET_NUMBER));
		visit.setAddressZipCode(((Long)entity.getProperty(Visit.ADDRESS_ZIP_CODE)).intValue());
		visit.setFromDate((Date)entity.getProperty(Visit.FROM_DATE));
		visit.setStatus(StatusEnum.valueOf((String)entity.getProperty(Visit.STATUS)));
		visit.setToDate((Date)entity.getProperty(Visit.TO_DATE));
		visit.setVisitDescription((String)entity.getProperty(Visit.VISIT_DESCRIPTION));
		
		return visit;
	}

	@Override
	public Visit createVisit(Visit visit) throws EntityNotFoundException{
		Entity visitEntity = mapToEntity(visit);
		
		Key key = ds.put(visitEntity);
		
		return mapEntityToObject(ds.get(key));
	}

	@Override
	public Visit readVisit(String id) throws EntityNotFoundException{
		return mapEntityToObject(ds.get(stringToKey(id)));
	}

	@Override
	public List<Visit> readAllVisits() {
		List<Visit> visits = new ArrayList<Visit>();
		Query query = new Query(Visit.KIND);
		PreparedQuery preparedQuery = ds.prepare(query);
		
		for(Entity entity : preparedQuery.asIterable()){
			visits.add(mapEntityToObject(entity));
		}
		return visits;
	}

	@Override
	public void updateVisit(Visit visit) throws EntityNotFoundException{
		//Get the existing entity
		Key key = stringToKey(visit.getKey());
		Entity entity = ds.get(key);
		
		//Update the existing entity with our new fields
		Entity entityToUpdate = mapToEntity(visit, entity);
		//Update the entity
		ds.put(entityToUpdate);
	}

	@Override
	public List<Visit> getVisitsForSchedule(String scheduleId) {
		List<Visit> visitsForSchedule = new ArrayList<Visit>();
		
		Query query = new Query(Visit.KIND);
		query.setFilter(new Query.FilterPredicate(Visit.SCHEDULE_KEY, FilterOperator.EQUAL, stringToKey(scheduleId)));
		
		PreparedQuery preparedQuery = ds.prepare(query);
		for(Entity entity : preparedQuery.asIterable()){
			visitsForSchedule.add(mapEntityToObject(entity));
		}
		
		return visitsForSchedule;
	}
}
