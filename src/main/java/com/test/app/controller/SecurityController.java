package com.test.app.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/security/")
@CrossOrigin("http://localhost:3000")
public class SecurityController {
	@GetMapping("csrf")
	public CsrfToken csrf(CsrfToken token) {
		return token;
	}
}