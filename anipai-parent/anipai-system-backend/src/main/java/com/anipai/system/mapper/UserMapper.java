package com.anipai.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.anipai.system.domain.SysUser;
import com.anipai.utils.Pagination;

@Mapper
public interface UserMapper {
	
	@Select("select user_id, user_name, password, role_id, agency_id "
			+ "from sys_user where user_name = #{userName}")
	@Results({
		@Result(column = "role_id", property = "sysRole",
			one = @One(select = "com.anipai.system.mapper.RoleMapper.findRole")),
		@Result(column = "agency_id", property = "agency",
			one = @One(select = "com.anipai.agency.mapper.AgencyMapper.findAgency"))
	})
	SysUser findUserByUserName(String userName);
	
	@Select("select user_id, user_name, role_id from sys_user "
			+ "where user_id = #{userId}")
	@Results({
		@Result(column = "role_id", property = "sysRole",
			one = @One(select = "com.anipai.system.mapper.RoleMapper.findRole"))
	})
	SysUser findUser(Long userId);

	@SelectProvider(type = UserMapperProvider.class, method = "findUserPage")
	@Results({
		@Result(column = "role_id", property = "sysRole",
			one = @One(select = "com.anipai.system.mapper.RoleMapper.findRole"))
	})
	List<SysUser> findUserPage(@Param("page") Pagination pagination, @Param("agencyId") Long agencyId);

	@SelectProvider(type = UserMapperProvider.class, method = "total")
	int total(@Param("agencyId") Long agencyId);

	@Insert("insert into sys_user(user_name, password, role_id, agency_id) "
			+ "values (#{userName}, #{password}, #{sysRole.roleId}, #{agency.agencyId})")
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	void addUser(SysUser user);
	
	@Update("update sys_user set user_name=#{userName}, role_id=#{sysRole.roleId} where user_id=#{userId}")
	void updateUser(SysUser user);

	@Delete("delete from sys_user where user_id=#{userId}")
	void deleteUser(Long userId);
}
