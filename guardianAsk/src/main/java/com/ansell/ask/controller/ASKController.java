package com.ansell.ask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ASKController {

	@RequestMapping("/index")
	public String view() {
		return "askContainer.tile";
	}
}
