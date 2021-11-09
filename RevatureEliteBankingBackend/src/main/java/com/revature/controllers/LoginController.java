package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AuthRequest;
import com.revature.services.UserLoginDetailService;
import com.revature.utils.JwtUtil;


@RestController
@RequestMapping(value="/login")
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class LoginController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@GetMapping
	public ResponseEntity success() {
		return ResponseEntity.ok("successfully done");
	}
	
	/*
	 * User Login Authentication and JWT Token sent with response
	 */
	@PostMapping
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authrequest) throws Exception {
		try {
			
			
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authrequest.getUsername(),authrequest.getPassword() )
			);
		} catch (BadCredentialsException e) {

			return ResponseEntity
		            .status(HttpStatus.FORBIDDEN)
		            .body("Invalid Username/Password");
		}
		
		String jwt =  jwtUtil.generateToken(authrequest.getUsername());
//		return ResponseEntity.ok().body(jwt);
		return ResponseEntity.status(200).body(jwt);
		
	}
}
