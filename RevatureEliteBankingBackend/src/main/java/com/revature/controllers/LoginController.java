package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AuthRequest;
import com.revature.utils.JwtUtil;

@RestController
@RequestMapping(value="/login")
@CrossOrigin
public class LoginController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@GetMapping
	public ResponseEntity success() {
		return ResponseEntity.ok("successfully done");
	}
	
	@PostMapping(value = "/validate")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authrequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authrequest.getUsername(),authrequest.getPassword() )
			);
		} catch (BadCredentialsException e) {

			return ResponseEntity
		            .status(HttpStatus.FORBIDDEN)
		            .body("Error Message");
		}
		
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		String jwt =  jwtUtil.generateToken(authrequest.getUsername());
		return ResponseEntity.ok(jwt);
//		return (ResponseEntity) ResponseEntity.ok().header("jwt", "100");
		
	}
}