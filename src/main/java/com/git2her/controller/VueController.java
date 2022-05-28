package com.git2her.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git2her.config.AppProperty;

@Controller
@RequestMapping(value = "/vue")
public class VueController {

//	private final static Logger LOGGER = LogManager.getLogger(VueController.class);

	@Autowired
	AppProperty appProperty;

	@RequestMapping("")
	public String vue(Map<String, Object> model) {
		return "vueApp.html";
	}
}
