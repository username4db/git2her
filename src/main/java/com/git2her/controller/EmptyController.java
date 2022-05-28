package com.git2her.controller;

import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git2her.config.AppProperty;
import com.git2her.config.ConfigProperty;

@Controller
@RequestMapping(value = "/empty")
public class EmptyController {

	private final static Logger LOGGER = LogManager.getLogger(EmptyController.class);

	@Autowired
	AppProperty appProperty;

	@Autowired
	ConfigProperty configProperty;

	@RequestMapping("")
	public String empty(Map<String, Object> model) {
		LOGGER.info(TimeZone.getDefault().getDisplayName());
		LOGGER.info(Locale.getDefault().getDisplayName());
		return "empty.html";
	}

	@RequestMapping("test")
	public ResponseEntity<String> test(RequestEntity<String> req) {

		return ResponseEntity //
				.status(HttpStatus.OK) //
				.header("", "") //
				.contentType(MediaType.TEXT_PLAIN) //
				.body(configProperty.toString() + "\n" + appProperty.toString());
	}

}
