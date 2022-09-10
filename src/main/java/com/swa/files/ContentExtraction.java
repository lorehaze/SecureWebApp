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

	// regex inject value to find
	private static final String REGEX_FILE_CONTENT = "b\\ciao1\\b";

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
			// flagRegex = regexChecker(file);
//			System.out.println("IS TAMPERED: " + flagType);
//			System.out.println("IS INJECTED: " + flagRegex);
//			System.out.println("IS SECURE: " + flagSecure);
		}

		// System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
		return flagSecure;
	}

	public final boolean regexChecker(String path) throws IOException {
		boolean isInjected = false;

		System.out.println("RECEIVED PATH: " + path);

//		Check if file comes to function
//		boolean debug = true;
//
//		if (file != null) {
//			debug = false;
//		}
//		System.out.println("FILE NOT CAME TO REGEX: " + debug);

		path = "/Users/lorenzo/Documents/GitHub/SecureWebApp/src/main/webapp/uploads/testo.txt";

		BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
		// BufferedReader br = new BufferedReader(new InputStreamReader(file,
		// StandardCharsets.UTF_8));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			// while (line != null) {
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			System.out.println("CIAOOOOO: " + everything);
		} finally {
			br.close();
		}

		return isInjected;
	}

	/// /// /// /// /// /// /// /// /// /// /// /// /// /// /// /// /// /// /// ///
	/// /// /// ///
	/*
	 * private final boolean regexChecker(InputStream file) throws IOException {
	 * boolean isInjected = false;
	 * 
	 * if (file != null) { // BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(file)); BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(file)); while (reader.ready()) { // if buffer is ready
	 * String lines = reader.readLine(); System.out.println("LINES: " + lines);
	 * Pattern regex = Pattern.compile(REGEX_FILE_CONTENT,Pattern.CASE_INSENSITIVE);
	 * Matcher match = regex.matcher(lines); if (match.find()) isInjected = true; //
	 * file is injected } reader.close(); } return isInjected; }
	 */
	/// /// /// /// /// /// /// /// /// /// /// /// /// /// /// /// /// /// /// ///
	/// /// /// ///
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
