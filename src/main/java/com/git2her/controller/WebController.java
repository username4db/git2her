package com.git2her.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git2her.config.AppProperty;
import com.git2her.utils.MD5;

@Controller
@RequestMapping(value = "/")
public class WebController {

//	private final static Logger LOGGER = LogManager.getLogger(WebController.class);

	@Autowired
	AppProperty appProperty;

	@RequestMapping("")
	public String index() {
		return "index.html";
	}

	@RequestMapping("motp")
	public String motp(Map<String, Object> model) {
		MD5 hash = new MD5(StringUtils.substring(Long.toString(Instant.now().getEpochSecond()), 0, -1) //
				+ appProperty.getSecret() //
				+ appProperty.getPIN());
		model.put("hash", hash.asHex().substring(0, 6));
		model.put("version", appProperty.getVersion());
		return "motp.html";
	}

	@RequestMapping("motp/{secret}/{PIN}")
	public String motp(Map<String, Object> model, //
			@PathVariable(value = "secret") String secret, //
			@PathVariable(value = "PIN") String PIN) {
		MD5 hash = new MD5(StringUtils.substring(Long.toString(Instant.now().getEpochSecond()), 0, -1) //
				+ secret //
				+ PIN);
		model.put("hash", hash.asHex().substring(0, 6));
		model.put("version", appProperty.getVersion());
		return "motp.html";
	}
}
