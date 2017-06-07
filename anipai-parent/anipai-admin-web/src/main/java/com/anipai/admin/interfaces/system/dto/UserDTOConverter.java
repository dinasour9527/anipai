package com.anipai.admin.interfaces.system.dto;

import java.util.ArrayList;
import java.util.List;

import com.anipai.system.domain.SysUser;

public class UserDTOConverter {
	public static UserDTO toDTO(SysUser user) {
		return new UserDTO(user.getUserId(), user.getUserName(),
				user.getSysRole().getRoleId(), user.getSysRole().getRoleName());
	}
	
	public static List<UserDTO> toDTOList(List<SysUser> userList) {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		for(SysUser user : userList){
			userDTOList.add(UserDTOConverter.toDTO(user));
		}
		return userDTOList;
	}
}
