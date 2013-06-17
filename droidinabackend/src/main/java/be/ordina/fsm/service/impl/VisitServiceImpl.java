package be.ordina.fsm.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.EntityNotFoundException;

import be.ordina.fsm.dao.VisitDao;
import be.ordina.fsm.domain.Visit;
import be.ordina.fsm.service.VisitService;

@Service
public class VisitServiceImpl implements VisitService{

	private VisitDao visitDao;
	
	@Inject
	public void setServiceDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	@Override
	public Visit createVisit(Visit visit) {
		Visit createdVisit = null;
		try {
			createdVisit = visitDao.createVisit(visit);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return createdVisit;
	}

	@Override
	public Visit readVisit(String id) {
		Visit foundVisit = null;
		try {
			foundVisit = visitDao.readVisit(id);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return foundVisit;
	}

	@Override
	public List<Visit> readAllVisits() {
		return visitDao.readAllVisits();
	}

	@Override
	public void updateVisit(Visit visit) {
		try {
			visitDao.updateVisit(visit);
		} catch (EntityNotFoundException e) {
			//For the moment, do nothing -> think of a way to send an http-error code to client
			e.printStackTrace();
		}
	}

	@Override
	public List<Visit> getVisitsForSchedule(String scheduleId) {
		List<Visit> foundVisits = visitDao.getVisitsForSchedule(scheduleId);
		
		return foundVisits;
	}
	
	
}
