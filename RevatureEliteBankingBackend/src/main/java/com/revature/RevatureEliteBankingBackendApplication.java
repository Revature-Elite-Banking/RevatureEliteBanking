package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.revature.security.JWTAuthorizationFilter;

@SpringBootApplication
public class RevatureEliteBankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevatureEliteBankingBackendApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.csrf().disable()
//				.cors().and()
//				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//				.authorizeRequests()
//				.antMatchers(HttpMethod.POST, "/login", "/user/register").permitAll()
//				.anyRequest().authenticated();
//		}
		//this will make it unauthorized to sent any request except a POST to the URIs /login and /register
		//that is unless we send in a JWT to the response body using that controller
		//so be careful when testing
	}
}
