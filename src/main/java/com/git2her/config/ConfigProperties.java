package com.git2her.config;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ //
		@PropertySource("classpath:config/config.properties") //
})
public class ConfigProperties {

	private class Credentials {
		private String authMethod;
		private String username;
		private String password;
	}

	private String host;
	private int port;
	private String from;
	private List<String> defaultRecipients;
	private Map<String, String> additionalHeaders;
	private Credentials credentials;
}