package com.anipai.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.anipai.system.domain.SysAuthority;

@Mapper
public interface AuthorityMapper {
	
	@Select("select authority_id, authority_code, authority_name from sys_authority")
	List<SysAuthority> findAll();
	
	@Select("select sa.authority_id, sa.authority_code, sa.authority_name "
			+ "from sys_role_authority sra, sys_authority sa "
			+ "where sra.authority_id = sa.authority_id "
			+ "and sra.role_id = #{roleId}")
	List<SysAuthority> findAuthorityByRoleId(Long roleId);
}
