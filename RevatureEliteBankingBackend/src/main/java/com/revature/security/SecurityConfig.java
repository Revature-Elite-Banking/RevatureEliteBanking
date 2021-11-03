//package com.revature.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	UserDetailsService userDetailService; 
//	
//	
//	public AuthenticationProvider authProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailService);
//		provider.setPasswordEncoder(new BCryptPasswordEncoder());
//		return provider;
//		
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailService);
//	}
//
//
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.csrf().disable()
//		.cors().and()
//		.authorizeRequests()
//			.antMatchers(HttpMethod.POST, "/", "/user","/account").permitAll()
//			.anyRequest().authenticated();
//			
//	}
//}
