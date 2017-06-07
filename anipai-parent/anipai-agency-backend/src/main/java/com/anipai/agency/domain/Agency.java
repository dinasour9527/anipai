package com.anipai.agency.domain;

public class Agency {
	private Long agencyId;
	private String agencyName;
	
	public Agency() {}
	public Agency(String agencyName) {
		this.agencyName = agencyName;
	}
	public Agency(Long agencyId, String agencyName) {
		this.agencyId = agencyId;
		this.agencyName = agencyName;
	}
	
	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
}
