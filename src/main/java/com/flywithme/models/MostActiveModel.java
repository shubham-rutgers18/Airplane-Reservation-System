package com.flywithme.models;

public class MostActiveModel{

	private String airlineName;
	private String flightId;
	private int workingDays;

	public MostActiveModel(String airlineName, String flightId, int workingDays) {
		super();
		this.airlineName = airlineName;
		this.flightId = flightId;
		this.workingDays = workingDays;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public int getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(int workingDays) {
		this.workingDays = workingDays;
	}

}
