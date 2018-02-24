package com.logan.sftp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sftp")
public class SftpConfig {

	private String server;
	private String username;
	private String password;
	private String privateKeyUri;
	private String pollDir;
	private String pollDelay;
	private long shutdownTimeout = 10;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
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

	public String getPrivateKeyUri() {
		return privateKeyUri;
	}

	public void setPrivateKeyUri(String privateKeyUri) {
		this.privateKeyUri = privateKeyUri;
	}

	public String getPollDir() {
		return pollDir;
	}

	public void setPollDir(String pollDir) {
		this.pollDir = pollDir;
	}

	public String getPollDelay() {
		return pollDelay;
	}

	public void setPollDelay(String pollDelay) {
		this.pollDelay = pollDelay;
	}

	public long getShutdownTimeout() {
		return shutdownTimeout;
	}

	public void setShutdownTimeout(long shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
	}

}
