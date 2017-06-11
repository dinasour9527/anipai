package com.anipai.passport.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anipai.passport.domain.Customer;
import com.anipai.passport.mapper.CustomerMapper;

@Service
@Transactional
public class CustomerDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomerMapper custommerMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = custommerMapper.findCustomerByCustomerName(username);
		if(customer != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
			if(customer.getAgency() != null) {
				return new CustomerDetails(customer.getCustomerName(), customer.getPassword(), authorities,
						customer.getCustomerId(), customer.getAgency().getAgencyId());
			} else {
				return new CustomerDetails(customer.getCustomerName(), customer.getPassword(), authorities,
						customer.getCustomerId(), null);
			}
		}
		throw new UsernameNotFoundException("User: " + username + " not found.");
	}

	
}
