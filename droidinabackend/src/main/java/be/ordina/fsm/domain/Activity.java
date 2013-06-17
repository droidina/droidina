package be.ordina.fsm.domain;

public class Activity {
	public static final String KIND="activity";
	public static final String VISIT="visit";
	public static final String ACTIVITY_DESCRIPTION="activityDescription";
	public static final String ACTIVITY_TYPE="activityType";
	public static final String STATUS="status";
	
	private String key;
	private String visitKey;
	
	private String activityDescription;
	private String activityType;
	private StatusEnum status;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getVisitKey() {
		return visitKey;
	}
	public void setVisitKey(String visitKey) {
		this.visitKey = visitKey;
	}
	
	public String getActivityDescription() {
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
}
