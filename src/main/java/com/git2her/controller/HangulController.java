package com.git2her.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.git2her.dto.hangul.ReqHangul;
import com.git2her.dto.hangul.ResHangul;
import com.git2her.service.HangulService;

@RestController
@RequestMapping(value = "/hangul")
public class HangulController {

	private final static Logger LOGGER = LogManager.getLogger(HangulController.class);

	@Autowired
	private HangulService service;

	@RequestMapping(value = "/syllable" //
			, method = RequestMethod.POST //
			, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE } //
			, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE })
	@ResponseBody
	ResHangul doSyllable(@RequestBody ReqHangul req) {
		ResHangul res = new ResHangul();
		LOGGER.info(req.toString());
		res.setChracters(service.syllable(req.getCharacters()));
		LOGGER.info(res.toString());
		return res;
	}

	@RequestMapping(value = "/syllable/{chars}" //
			, method = RequestMethod.GET)
	ResHangul doGet(@PathVariable String chars) {
		ResHangul res = new ResHangul();
		LOGGER.info(chars);
		res.setChracters(service.syllable(chars));
		LOGGER.info(chars);
		return res;
	}

}
