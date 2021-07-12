package com.neueda.urlshortner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UrlControllerView {
	
	@GetMapping(value="/")
	public String loadIndex() {
		return "index";
	}

}
