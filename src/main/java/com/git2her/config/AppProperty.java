package com.git2her.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperty {

	@Value("${app.version:2022-05-28}")
	private String appVersion;

	@Value("${app.secret:2022-05-28}")
	private String secret;

	@Value("${app.PIN:40e2936b22f5e806}")
	private String PIN;

	public String getSecret() {
		return secret;
	}

	public String getPIN() {
		return PIN;
	}

	public String getAppVersion() {
		return appVersion;
	}

}
