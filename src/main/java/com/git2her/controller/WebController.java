package com.git2her.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git2her.utils.MD5;
import com.git2her.utils.qrcode.QrCode;
import com.git2her.utils.qrcode.QrSegment;

@Controller
@RequestMapping(value = "/")
public class WebController {

	private final static Logger LOGGER = LogManager.getLogger(WebController.class);

	@RequestMapping("/vue")
	public String vue(Map<String, Object> model) {
		return "vueApp.html";
	}

	@RequestMapping("/empty")
	public String empty(Map<String, Object> model) {
		LOGGER.info(TimeZone.getDefault().getDisplayName());
		LOGGER.info(Locale.getDefault().getDisplayName());
		return "empty.html";
	}

	@RequestMapping("/test")
	public ResponseEntity<String> test(RequestEntity<String> req) {
		return ResponseEntity //
				.status(HttpStatus.OK) //
				.header("", "") //
				.contentType(MediaType.TEXT_PLAIN) //
				.body(req.getBody());
	}

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
		model.put("motp", hash.asHex().substring(0, 6));
		return "motp.html";
	}

	@RequestMapping("/1922/{data}")
	public ResponseEntity<byte[]> qrcode1922(@PathVariable(value = "data") String data) throws IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("smsto:1922:location:");
		sb.append(StringUtils.substring(data, 0, 3));
		sb.append(" ");
		sb.append(StringUtils.substring(data, 4, 7));
		sb.append(" ");
		sb.append(StringUtils.substring(data, 8, 11));
		sb.append(" ");
		sb.append(StringUtils.substring(data, 12));

		QrCode qr = QrCode.encodeText(sb.toString(), QrCode.Ecc.MEDIUM);
		BufferedImage img = qr.toImage(4, 10);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "png", baos);
		// ImageIO.write(img, "png", new File("qr-code.png"));
		byte[] imageInByte = baos.toByteArray();
		return ResponseEntity //
				.status(HttpStatus.OK) //
				.header(HttpHeaders.CONTENT_DISPOSITION, "filename='qrcode.png'") //
				.contentType(MediaType.IMAGE_PNG) //
				.body(imageInByte);
	}

	@RequestMapping("/qrcode/{data}")
	public ResponseEntity<byte[]> qrcode(@PathVariable(value = "data") String data) throws IOException {

		QrCode qr = QrCode.encodeText(data, QrCode.Ecc.MEDIUM);
		BufferedImage img = qr.toImage(4, 10);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "png", baos);
		// ImageIO.write(img, "png", new File("qr-code.png"));
		byte[] imageInByte = baos.toByteArray();
		return ResponseEntity //
				.status(HttpStatus.OK) //
				.header(HttpHeaders.CONTENT_DISPOSITION, "filename='qrcode.png'") //
				.contentType(MediaType.IMAGE_PNG) //
				.body(imageInByte);
	}

	@RequestMapping("/qr/{data}")
	public String qr(Map<String, Object> model, @PathVariable(value = "data") String data) throws IOException {

		QrCode qr = QrCode.encodeText(data, QrCode.Ecc.MEDIUM);

		List<QrSegment> segs = QrSegment.makeSegments(data);
		qr = QrCode.encodeSegments(segs, QrCode.Ecc.HIGH, 5, 5, 2, false);
		model.put("qr", qr);
		for (int y = 0; y < qr.size; y++) {
			for (int x = 0; x < qr.size; x++) {
				qr.getModule(x, y);
			}
		}
		return "qr.html";
	}

}
