
package com.revature.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailService; 
	
	
	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.cors()
		.and()
		.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/**","/login","/user").permitAll()
			.antMatchers(HttpMethod.GET, "/**").permitAll()
			.anyRequest().authenticated();
			


		
		http.addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	

	

	
}