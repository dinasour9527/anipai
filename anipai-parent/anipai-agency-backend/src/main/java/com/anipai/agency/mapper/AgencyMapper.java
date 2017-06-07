package com.anipai.agency.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.anipai.agency.domain.Agency;
import com.anipai.utils.Pagination;

@Mapper
public interface AgencyMapper {
	
	@Select("select agency_id, agency_name from agency "
			+ "order by ${sort} ${order} "
			+ "limit #{offset}, #{limit}")
	List<Agency> findAgencyPage(Pagination pagination);
	
	@Select("select count(agency_id) from agency")
	int total();

	@Select("select agency_id, agency_name from agency where agency_id=#{agencyId}")
	Agency findAgency(Long agencyId);

	@Insert("insert into agency(agency_name) values (#{agencyName})")
	@Options(useGeneratedKeys = true, keyProperty = "agencyId", keyColumn = "agency_id")
	void addAgency(Agency agency);

	@Update("update agency set agency_name=#{agencyName} where agency_id=#{agencyId}")
	void updateAgency(Agency agency);

	@Delete("delete from agency where agency_id=#{agencyId}")
	void deleteAgency(Long agencyId);
}
