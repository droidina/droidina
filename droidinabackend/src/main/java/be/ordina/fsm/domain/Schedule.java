package be.ordina.fsm.domain;

import java.util.Date;
import java.util.List;

import be.ordina.fsm.domain.Schedule;

import com.google.appengine.api.datastore.Key;

public class Schedule {
	public static final String KIND="schedule";
	public static final String AGENT="agent";
	public static final String SCHEDULE_DESCRIPTION="scheduleDescription";
	public static final String FROM_DATE="fromDate";
	public static final String TO_DATE="toDate";
	public static final String VISITS="visits";
	
	private String key;
	private String agentEntityKey;
	
	private String scheduleDescription;
	private Date fromDate;
	private Date toDate;
	
	private List<String> visitEntityKeys;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getScheduleDescription() {
		return scheduleDescription;
	}
	public void setScheduleDescription(String scheduleDescription) {
		this.scheduleDescription = scheduleDescription;
	}

	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public String getAgentEntityKey() {
		return agentEntityKey;
	}
	public void setAgentEntityKey(String agentEntityKey) {
		this.agentEntityKey = agentEntityKey;
	}
	
	public List<String> getVisitEntityKeys() {
		return visitEntityKeys;
	}
	public void setVisitEntityKeys(List<String> visitEntityKeys) {
		this.visitEntityKeys = visitEntityKeys;
	}
}
