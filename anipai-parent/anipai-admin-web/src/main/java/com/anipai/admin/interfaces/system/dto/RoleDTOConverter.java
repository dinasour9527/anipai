package com.anipai.admin.interfaces.system.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.system.domain.SysRole;

public class RoleDTOConverter {
	public static RoleDTO toDTO(SysRole role) {
		return new RoleDTO(role.getRoleId(), role.getRoleCode(), role.getRoleName());
	}
	
	public static List<RoleDTO> toDTOList(List<SysRole> roleList) {
		List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
		for(SysRole role : roleList){
			roleDTOList.add(RoleDTOConverter.toDTO(role));
		}
		return roleDTOList;
	}
}
