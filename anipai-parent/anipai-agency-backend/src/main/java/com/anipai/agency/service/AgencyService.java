package com.anipai.agency.service;

import java.util.Map;

import com.anipai.agency.domain.Agency;

public interface AgencyService {

	Map<String, Object> getAgencyListPage(String sort, String order, int offset, int limit);

	Long createAgency(String agencyName);

	Agency getAgency(Long agencyId);

	void editAgency(Long agencyId, String agencyName);

	void deleteAgency(Long agencyId);

}
