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

		public String getAuthMethod() {
			return authMethod;
		}

		public void setAuthMethod(String authMethod) {
			this.authMethod = authMethod;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getDefaultRecipients() {
		return defaultRecipients;
	}

	public void setDefaultRecipients(List<String> defaultRecipients) {
		this.defaultRecipients = defaultRecipients;
	}

	public Map<String, String> getAdditionalHeaders() {
		return additionalHeaders;
	}

	public void setAdditionalHeaders(Map<String, String> additionalHeaders) {
		this.additionalHeaders = additionalHeaders;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

}