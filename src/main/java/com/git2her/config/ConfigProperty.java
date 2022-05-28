package com.git2her.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ //
		@PropertySource("classpath:config/config.properties") //
})
@ConfigurationProperties(prefix = "mail")
public class ConfigProperty {

	private class Credentials {
		private String authMethod;
		private String username;
		private String password;

		@Override
		public String toString() {
			return "Credentials [authMethod=" + authMethod + ", username=" + username + ", password=" + password + "]";
		}

	}

	private String host;
	private int port;
	private String from;
	private List<String> defaultRecipients;
	private Map<String, String> additionalHeaders;
	private Credentials credentials;

	@Override
	public String toString() {
		return "ConfigProperty [host=" + host + ", port=" + port + ", from=" + from + ", defaultRecipients="
				+ defaultRecipients + ", additionalHeaders=" + additionalHeaders + ", credentials=" + credentials + "]";
	}

}