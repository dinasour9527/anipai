package com.anipai.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.anipai.system.domain.SysRoleAuthority;

@Mapper
public interface RoleAuthorityMapper {
	
	@InsertProvider(type = RoleAuthorityMapperProvider.class, method = "insertAll")  
	void insertAll(@Param("list") List<SysRoleAuthority> list);
	
	@Delete("delete from sys_role_authority where role_id=#{roleId}")
	void deleteByRoleId(Long roleId);
}
