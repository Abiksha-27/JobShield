package com.jobshield.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobshield.dto.RegisterRequest;
import com.jobshield.service.AuthService;
import com.jobshield.dto.LoginRequest;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	 @Autowired
	    private AuthService authService;

	    @PostMapping("/register")
	    public String register(
	            @RequestBody RegisterRequest request) {

	        return authService.registerUser(request);
	    }
	    @PostMapping("/login")
	    public String login(
	            @RequestBody LoginRequest request) {

	        return authService.loginUser(request);
	    }
	

}
