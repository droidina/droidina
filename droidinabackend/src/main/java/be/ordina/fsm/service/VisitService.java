package be.ordina.fsm.service;

import java.util.List;

import be.ordina.fsm.domain.Visit;

public interface VisitService {

	public Visit createVisit(Visit visit);
	public Visit readVisit(String id);
	public List<Visit> readAllVisits();
	public void updateVisit(Visit visit);
	
	public List<Visit> getVisitsForSchedule(String scheduleId);
}
