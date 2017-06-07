package com.anipai.system.security;

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

import com.anipai.system.domain.SysAuthority;
import com.anipai.system.domain.SysUser;
import com.anipai.system.mapper.AuthorityMapper;
import com.anipai.system.mapper.UserMapper;

@Service
@Transactional
public class SysUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = userMapper.findUserByUserName(username);
		if(user != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if(user.getSysRole() != null) {
				List<SysAuthority> sysAuthorities = authorityMapper.findAuthorityByRoleId(user.getSysRole().getRoleId());
				if(!sysAuthorities.isEmpty()) {
					for(SysAuthority sysAuthority : sysAuthorities) {
						authorities.add(new SimpleGrantedAuthority(sysAuthority.getAuthorityCode()));
					}
				}
			}
			if(user.getAgency() != null) {
				return new SysUserDetails(user.getUserName(), user.getPassword(), authorities,
						user.getUserId(), user.getAgency().getAgencyId(), user.getSysRole().getRoleName());
			} else {
				return new SysUserDetails(user.getUserName(), user.getPassword(), authorities,
						user.getUserId(), null, user.getSysRole().getRoleName());
			}
			//return new User(user.getUserName(), user.getPassword(), authorities);
		}
		throw new UsernameNotFoundException("User: " + username + " not found.");
	}

}
