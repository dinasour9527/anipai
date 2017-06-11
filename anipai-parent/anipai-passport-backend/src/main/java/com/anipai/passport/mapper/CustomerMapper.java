package com.anipai.passport.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.anipai.passport.domain.Customer;
import com.anipai.utils.Pagination;

@Mapper
public interface CustomerMapper {

	@Insert("insert into customer(customer_name, password, email, agency_id) "
			+ "values (#{customerName}, #{password}, #{email}, #{agency.agencyId})")
	@Options(useGeneratedKeys = true, keyProperty = "customerId", keyColumn = "customer_id")
	void insertCustomer(Customer customer);

	@SelectProvider(type = CustomerMapperProvider.class, method = "findCustomerPage")
	List<Customer> findCustomerPage(@Param("page") Pagination pagination, @Param("agencyId") Long agencyId);

	@SelectProvider(type = CustomerMapperProvider.class, method = "total")
	int total(@Param("agencyId") Long agencyId);

	@Select("select customer_id, customer_name, password, email, agency_id "
			+ "from customer where customer_name = #{customerName}")
	@Results({
		@Result(column = "agency_id", property = "agency",
			one = @One(select = "com.anipai.agency.mapper.AgencyMapper.findAgency"))
	})
	Customer findCustomerByCustomerName(String customerName);

}
