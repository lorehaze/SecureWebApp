package com.swa.files;

public class URLBuilder {
	/*
	 * URL Builder
	 */
	private String openingTag = "<h4>";
	private String enclosingTag = "</a></h4>";
	private String hrefPart = "<a href=\"";
	private String hrefAfter = "\">";
	
	public String buildURL(String path, String fileName) {
		String fileURL = null;
		
		fileURL = openingTag+hrefPart+path+fileName+hrefAfter+fileName+enclosingTag;
		
		return fileURL;
	}
	
}
