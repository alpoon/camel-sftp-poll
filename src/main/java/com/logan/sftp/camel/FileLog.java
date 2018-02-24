package com.logan.sftp.camel;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileMessage;
import org.springframework.stereotype.Component;

/**
 * Keep track of last modified times of processed files.
 * @author Alfred Poon
 *
 */
@Component("fileLog")
public class FileLog implements Processor {
	
	private Map<String, Long> fileLastModified = new HashMap<>();

	public boolean isModified(String fileName, Long lastModified) {
		Long knownLastModified = fileLastModified.get(fileName);
		return knownLastModified == null || knownLastModified.longValue() < lastModified.longValue();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void process(Exchange exchange) throws Exception {
		GenericFileMessage message = (GenericFileMessage) exchange.getIn();
		GenericFile file = message.getGenericFile();
		String fileName = file.getFileName();
		Long currentLastModified = file.getLastModified();
		fileLastModified.put(fileName, currentLastModified);
	}
}
