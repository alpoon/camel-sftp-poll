package com.logan.sftp.camel;

import java.util.Map;

import org.apache.camel.processor.ErrorHandler;

/**
 * Map Throwable to ErrorHandler objects.
 * @author Alfred Poon
 *
 */
public interface CamelErrorHandlerFactory {
	
	  Map<Class<? extends Throwable>, ErrorHandler> get();

}
