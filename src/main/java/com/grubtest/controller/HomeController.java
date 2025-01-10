package com.grubtest.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

	@GetMapping()
	public String HomeControllerHandller() {
		
		return "this is my first word in spring boot";
	}
	
	@GetMapping("/Home")
    public String HomeControllerHandller2() {
		
		return "this is HomeControllerHandller2";
	}
}
