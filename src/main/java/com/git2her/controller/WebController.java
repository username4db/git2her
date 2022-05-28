package com.git2her.controller;

import java.time.Instant;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git2her.config.AppProperty;
import com.git2her.utils.MD5;

@Controller
@RequestMapping(value = "/")
public class WebController {

	private final static Logger LOGGER = LogManager.getLogger(WebController.class);

	@Autowired
	AppProperty appProperty;

	@RequestMapping("/")
	public String index() {
		return "index.html";
	}

	@RequestMapping("/motp/{secret}/{PIN}")
	public String motp(Map<String, Object> model, //
			@PathVariable(value = "secret") String secret, //
			@PathVariable(value = "PIN") String PIN) {
		MD5 hash = new MD5(StringUtils.substring(Long.toString(Instant.now().getEpochSecond()), 0, -1) //
				+ secret //
				+ PIN);
		model.put("hash", hash.asHex().substring(0, 6));
		model.put("version", appProperty.getAppVersion());
		return "motp.html";
	}

}
