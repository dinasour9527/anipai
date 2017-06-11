package com.anipai.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("customerDetailsServiceImpl")
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth
		.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("ADMIN");*/
		auth
		.userDetailsService(userDetailsService).passwordEncoder(new Md5PasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.headers().frameOptions().sameOrigin().and()
		.formLogin().loginPage("/passport/login").permitAll().and()
		.logout().permitAll().and()
		.csrf().disable()
		.authorizeRequests()
			.anyRequest().permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/css/**")
		.antMatchers("/favicon/**")
		.antMatchers("/images/**")
		.antMatchers("/js/**")
		.antMatchers("/lib/**");
	}
}
