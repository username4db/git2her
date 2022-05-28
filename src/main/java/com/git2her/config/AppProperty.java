package com.git2her.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ //
		@PropertySource("classpath:config/app.properties") //
})
public class AppProperty {

	@Override
	public String toString() {
		return "AppProperty [version=" + version + ", secret=" + secret + ", PIN=" + PIN + "]";
	}

	@Value("${app.version:unknown}")
	private String version;

	@Value("${app.motp.secret:40e2936b22f5e806}")
	private String secret;

	@Value("${app.motp.PIN:7783}")
	private String PIN;

	public String getSecret() {
		return secret;
	}

	public String getPIN() {
		return PIN;
	}

	public String getVersion() {
		return version;
	}

}
