package com.anipai.admin.interfaces.agency.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anipai.admin.interfaces.agency.dto.AgencyDTO;
import com.anipai.admin.interfaces.agency.dto.AgencyDTOConverter;
import com.anipai.admin.utils.PageModel;
import com.anipai.agency.domain.Agency;
import com.anipai.agency.service.AgencyService;
import com.anipai.system.service.RoleService;
import com.anipai.system.service.UserService;

@Controller
@RequestMapping("/agency")
public class AgencyController {
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/agencyManage", method = RequestMethod.GET)
	public String agencyManage() {
		return "agency/agencyManage";
	}
	
	@RequestMapping(value = "/agencyTable", method = RequestMethod.GET)
	@ResponseBody
	public PageModel<AgencyDTO> agencyTable(String sort, String order, int offset, int limit) {
		Map<String, Object> agencyListPage = agencyService.getAgencyListPage(sort, order, offset, limit);
		@SuppressWarnings("unchecked")
		List<Agency> agencyList = (List<Agency>) agencyListPage.get("list");
		PageModel<AgencyDTO> pageModel = new PageModel<AgencyDTO>();
		List<AgencyDTO> agencyDTOList = AgencyDTOConverter.toDTOList(agencyList);
		pageModel
			.setRows(agencyDTOList)
			.setTotal((int) agencyListPage.get("total"));
		return pageModel;
	}
	
	@RequestMapping(value = "/agencyEditForm/{agencyId}", method = RequestMethod.GET)
	@ResponseBody
	public AgencyDTO agencyEditForm(@PathVariable Long agencyId) {
		Agency agency = agencyService.getAgency(agencyId);
		AgencyDTO agencyDTO = AgencyDTOConverter.toDTO(agency);
		return agencyDTO;
	}
	
	@RequestMapping(value = "/agencyCreate", method = RequestMethod.POST)
	@ResponseBody
	public int agencyCreate(String agencyName) {
		Long agencyId = agencyService.createAgency(agencyName);
		Long defaultRoleId = roleService.createRole("AGENCY_ADMIN", "机构管理员", agencyId);
		Long[] authorityIds = new Long[]{new Long(5), new Long(6), new Long(7), new Long(8)};
		roleService.relateAuthority(defaultRoleId, authorityIds);
		userService.createUser("aa"+agencyId, defaultRoleId, agencyId);
		return 1;
	}
	
	@RequestMapping(value = "/agencyEdit", method = RequestMethod.POST)
	@ResponseBody
	public int agencyEdit(Long agencyId, String agencyName) {
		agencyService.editAgency(agencyId, agencyName);
		return 1;
	}
	
	@RequestMapping(value = "/agencyDelete", method = RequestMethod.POST)
	@ResponseBody
	public int agencyDelete(Long agencyId) {
		agencyService.deleteAgency(agencyId);
		return 1;
	}
}
