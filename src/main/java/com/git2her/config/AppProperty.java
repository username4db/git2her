package com.git2her.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperty {

	@Value("${app.version}")
	private String appVersion;

	public String getAppVersion() {
		return appVersion;
	}

}
