package com.anipai.admin.interfaces.agency.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.agency.domain.Agency;

public class AgencyDTOConverter {
	public static AgencyDTO toDTO(Agency agency) {
		return new AgencyDTO(agency.getAgencyId(), agency.getAgencyName());
	}
	
	public static List<AgencyDTO> toDTOList(List<Agency> agencyList) {
		List<AgencyDTO> agencyDTOList = new ArrayList<AgencyDTO>();
		for(Agency agency : agencyList){
			agencyDTOList.add(AgencyDTOConverter.toDTO(agency));
		}
		return agencyDTOList;
	}
}
