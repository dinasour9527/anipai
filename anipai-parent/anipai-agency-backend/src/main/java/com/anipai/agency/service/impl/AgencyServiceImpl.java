package com.anipai.agency.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.agency.domain.Agency;
import com.anipai.agency.mapper.AgencyMapper;
import com.anipai.agency.service.AgencyService;
import com.anipai.utils.Pagination;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService{
	
	@Autowired
	private AgencyMapper agencyMapper;

	@Override
	public Map<String, Object> getAgencyListPage(String sort, String order, int offset, int limit) {
		Map<String, Object> agencyListPage = new HashMap<String, Object>();
		Pagination pagination = new Pagination(sort, order, offset, limit/*, null*/);
		List<Agency> agencyList = agencyMapper.findAgencyPage(pagination);
		int total = agencyMapper.total();
		agencyListPage.put("list", agencyList);
		agencyListPage.put("total", total);
		return agencyListPage;
	}

	@Override
	public Agency getAgency(Long agencyId) {
		Agency agency = agencyMapper.findAgency(agencyId);
		return agency;
	}

	@Override
	public Long createAgency(String agencyName) {
		Agency agency = new Agency(agencyName);
		agencyMapper.addAgency(agency);
		return agency.getAgencyId();
	}

	@Override
	public void editAgency(Long agencyId, String agencyName) {
		Agency agency = new Agency(agencyId, agencyName);
		agencyMapper.updateAgency(agency);
	}

	@Override
	public void deleteAgency(Long agencyId) {
		agencyMapper.deleteAgency(agencyId);
	}

}
