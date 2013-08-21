package be.ordina.droidina.fsm.model;

import java.util.Date;

public class Visit {

	public static final String KIND = "Visit";
	public static final String VISIT_DESCRIPTION = "visitDescription";
	public static final String ADDRESS_STREET = "addressStreet";
	public static final String ADDRESS_CITY = "addressCity";
	public static final String ADDRESS_BOX = "addressBox";
	public static final String ADDRESS_ZIP_CODE = "addressZipCode";
	public static final String ADDRESS_STREET_NUMBER = "addressStreetNumber";
	public static final String FROM_DATE = "fromDate";
	public static final String TO_DATE = "toDate";
	public static final String STATUS = "status";
	
	public static final String SCHEDULE_KEY = "scheduleKey";
	
	private String key;
	//Foreign key
	private String scheduleKey;

	private String visitDescription;
	private String addressStreet;
	private String addressStreetNumber;
	private String addressCity;
	private String addressBox;
	private int addressZipCode;
	private Date fromDate;
	private Date toDate;
	private StatusEnum status;
	
//	private List<Activity> activities;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getScheduleKey() {
		return scheduleKey;
	}
	public void setScheduleKey(String scheduleKey) {
		this.scheduleKey = scheduleKey;
	}
	
	public String getVisitDescription() {
		return visitDescription;
	}
	public void setVisitDescription(String visitDescription) {
		this.visitDescription = visitDescription;
	}

	public String getAddressStreet() {
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}
	
	public String getAddressStreetNumber() {
		return addressStreetNumber;
	}
	public void setAddressStreetNumber(String addressStreetNumber) {
		this.addressStreetNumber = addressStreetNumber;
	}
	
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressBox() {
		return addressBox;
	}
	public void setAddressBox(String addressBox) {
		this.addressBox = addressBox;
	}

	public int getAddressZipCode() {
		return addressZipCode;
	}
	public void setAddressZipCode(int addressZipCode) {
		this.addressZipCode = addressZipCode;
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

	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}	
}
