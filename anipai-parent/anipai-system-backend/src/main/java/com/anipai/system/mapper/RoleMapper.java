package com.anipai.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.anipai.system.domain.SysRole;
import com.anipai.utils.Pagination;

@Mapper
public interface RoleMapper {

	//@SelectProvider("select role_id, role_code, role_name from sys_role")
	@SelectProvider(type = RoleMapperProvider.class, method = "findAllByAgencyId")
	List<SysRole> findAllByAgencyId(@Param("agencyId") Long agencyId);
	
	@Select("select role_id, role_code, role_name from sys_role where role_id=#{roleId}")
	SysRole findRole(Long roleId);
	
	@SelectProvider(type = RoleMapperProvider.class, method = "findRolePage") 
	List<SysRole> findRolePage(@Param("page") Pagination pagination, @Param("agencyId") Long agencyId);
	
	@SelectProvider(type = RoleMapperProvider.class, method = "total")
	int total(@Param("agencyId") Long agencyId);
	
	@Insert("insert into sys_role(role_code, role_name, agency_id) values (#{roleCode}, #{roleName}, #{agency.agencyId})")
	@Options(useGeneratedKeys = true, keyProperty = "roleId", keyColumn = "role_id")
	void addRole(SysRole sysRole);

	@Update("update sys_role set role_code=#{roleCode}, role_name=#{roleName} where role_id=#{roleId}")
	void updateRole(SysRole sysRole);

	@Delete("delete from sys_role where role_id=#{roleId}")
	void deleteRole(Long roleId);
}
