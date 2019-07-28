package com.sms.uploadservice.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="file")
public class FileStorageProperties {
	private static String uploadDir;

	public static String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		FileStorageProperties.uploadDir = uploadDir;
	}
	

}
