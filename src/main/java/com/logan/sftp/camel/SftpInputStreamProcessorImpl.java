package com.logan.sftp.camel;

import java.io.InputStream;

public class SftpInputStreamProcessorImpl implements SftpInputStreamProcessor {

	@Override
	public void processStream(InputStream inputStream) {
		System.out.println("handle input stream");
	}

}
