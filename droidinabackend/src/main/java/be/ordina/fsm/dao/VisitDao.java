package be.ordina.fsm.dao;

import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;

import be.ordina.fsm.domain.Visit;

public interface VisitDao {
	
	public Visit createVisit(Visit visit) throws EntityNotFoundException;
	public Visit readVisit(String id) throws EntityNotFoundException;
	public List<Visit> readAllVisits();
	public void updateVisit(Visit visit) throws EntityNotFoundException;

	public List<Visit> getVisitsForSchedule(String scheduleId);
}
