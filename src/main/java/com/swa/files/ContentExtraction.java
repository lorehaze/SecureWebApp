package com.swa.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

public class ContentExtraction {

	// regex pattern against LFI/XSS (escaped and unescaped)/SQLi
	public static final String REGEX_FILE_CONTENT_PATTERN = "(<script>|<\\/script>|\\.jsp|\\?[a-zA-Z]+=)";

	public boolean FileChecker(InputStream file, String ContentType) throws IOException {

		boolean flagSecure = false;
		boolean flagType = false;
		boolean flagRegex = false;
		long start = System.currentTimeMillis();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		Parser parser = new AutoDetectParser();
		try {
			parser.parse(file, handler, metadata, new ParseContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (ContentType) {
		case "text/plain":
			flagType = ctypeChecker(metadata, ContentType);
		}
		return flagSecure;
	}

	public final boolean regexChecker(File file) throws IOException {
		boolean isInjected = false;

		BufferedReader buff = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));

		String strCurrentLine = null;

		// check if file reaches function
		if (buff != null) {
			Pattern pattern = Pattern.compile(REGEX_FILE_CONTENT_PATTERN);
			// here i can read lines
			while ((strCurrentLine = buff.readLine()) != null) {
				System.out.println(strCurrentLine); // PRINT CURRENT LINE
				Matcher matcher = pattern.matcher(strCurrentLine);
				if (matcher.find()) {
					isInjected = true;
				}
			}
		}
		
		if (isInjected = true) {
			file.delete();
		}		
		return isInjected;
	}

	private final boolean ctypeChecker(Metadata metadata, String contentType) {
		boolean isTampered = false;
		String toCheck = null;
		if (metadata != null) {
			toCheck = metadata.get(metadata.CONTENT_TYPE); // get file content type
		}
		System.out.println(toCheck);
		if (!toCheck.contains(contentType)) {
			isTampered = true; // file is tampered
		}
		return isTampered;
	}

}
