package com.logan.sftp.camel;

import java.io.InputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;

public interface SftpInputStreamProcessor extends Processor {

	void processStream(InputStream inputStream);
	
	@Override
	default void process(Exchange exchange) {
		@SuppressWarnings("rawtypes")
		GenericFileMessage message = (GenericFileMessage) exchange.getIn();
		InputStream inputStream = message.getBody(InputStream.class);
		processStream(inputStream);
	}
}
