package com.anipai.admin.interfaces.agency.dto;

public class AgencyDTO {
	private Long agencyId;
	private String agencyName;
	
	public AgencyDTO() {}
	public AgencyDTO(Long agencyId, String agencyName) {
		this.agencyId = agencyId;
		this.agencyName = agencyName;
	}

	public Long getAgencyId() {
		return agencyId;
	}
	public AgencyDTO setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
		return this;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public AgencyDTO setAgencyName(String agencyName) {
		this.agencyName = agencyName;
		return this;
	}
	
}
