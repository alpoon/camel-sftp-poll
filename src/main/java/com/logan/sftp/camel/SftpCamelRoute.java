package com.logan.sftp.camel;

import java.util.Base64;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.logan.sftp.config.SftpConfig;

public class SftpCamelRoute extends RouteBuilder {

	@Autowired
	private SftpConfig sftpConfig;

	@Autowired
	private SftpInputStreamProcessor inputStreamProcessor;

	@Autowired(required = false)
	private CamelErrorHandlerFactory customErrorHandlers;
	
	/**
	 * Camel route to poll a target sftp folder and stream files in if they are new or modified.
	 * Optionally register a CamelErrorHandlerFactory that supplies an error handler for each throwable.
	 */
	@Override
	public void configure() throws Exception {
		getContext().getShutdownStrategy().setTimeout(sftpConfig.getShutdownTimeout());
		
		if (customErrorHandlers != null) {
			customErrorHandlers.get().forEach((throwable, handler) ->
			   onException(throwable)
			   .handled(true)
			   .process(handler)
			   .process("fileLog")
			   .log(throwable.getCanonicalName() + " caught while processing file ${file:name}"));
		}

		from(buildUri()).process(inputStreamProcessor).process("fileLog").log("Stream file ${file:name} complete");
	}

	private String buildUri() {
		StringBuilder uri = new StringBuilder("sftp://");
		uri.append(sftpConfig.getUsername() + "@" + sftpConfig.getServer());
		uri.append(sftpConfig.getPollDir());
		uri.append("?delay=" + sftpConfig.getPollDelay());
		if (!StringUtils.isEmpty(sftpConfig.getPrivateKeyUri())) {
			uri.append("&privateKeyUri=" + sftpConfig.getPrivateKeyUri());
		} else {
			uri.append("&password=" + new String(Base64.getDecoder().decode(sftpConfig.getPassword().getBytes())));
		}
		uri.append("&binary=true");
		uri.append("&streamDownload=true");
		uri.append("&filter=#allGenericFileFilters");
		return uri.toString();
	}
}
