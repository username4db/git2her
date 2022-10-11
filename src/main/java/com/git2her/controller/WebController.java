package com.git2her.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
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
	public String motp(Map<String, Object> model) throws NoSuchAlgorithmException {
		String n = Long.toString(Instant.now().getEpochSecond());
		String all = StringUtils.substring(n, 0, -1) //
				+ appProperty.getSecret() //
				+ appProperty.getPIN();
		MD5 hash = new MD5(all);

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(all.getBytes());
		byte[] digest = md.digest();

		model.put("hash", hash.asHex().substring(0, 6));
		model.put("version", appProperty.getVersion());
		model.put("newHash", all); //byteArrayToHex(digest).substring(0,6));
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

	public static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (byte b : a)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}
}
