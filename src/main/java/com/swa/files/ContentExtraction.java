package com.swa.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

public class ContentExtraction {

	// regex inject value to find
	public static final String REGEX_FILE_CONTENT = "(<script>|<\\/script>|\\.jsp|\\?[a-zA-Z]+=)";

//	public static void printMetadata(String filename) throws IOException, TikaException, Exception {
//		long start = System.currentTimeMillis();
//		BodyContentHandler handler = new BodyContentHandler();
//		Metadata metadata = new Metadata();
//		FileInputStream content = new FileInputStream(filename);
//		Parser parser = new AutoDetectParser();
//		try {
//			parser.parse(content, handler, metadata, new ParseContext());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		for (String name : metadata.names()) {
//			System.out.println(name + ":\t" + metadata.get(name));
//		}
//		System.out.println("...extracting file..." + (System.currentTimeMillis() - start));
//	}
/////////////////////////////////////////////////////////

	public void FileChecker(InputStream file, String ContentType) throws IOException {
		boolean flagType, flagRegex = false;
		long start = System.currentTimeMillis();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		Parser parser = new AutoDetectParser();
		try {
			parser.parse(file, handler, metadata, new ParseContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (String name : metadata.names()) {
			System.out.println(name + ":\t" + metadata.get(name));
		}

		switch (ContentType) {
		case "text/plain":
			flagType = ctypeChecker(metadata, ContentType);
			if (flagType = false) {
				flagRegex = regexChecker(file);
			} 
			System.out.println(flagType);
			System.out.println(flagRegex);
		}
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
	}

	private final boolean regexChecker(InputStream file) throws IOException {
		boolean isInjected = true;

		if (file != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(file));

			while (reader.ready()) { // if buffer is ready
				String lines = reader.readLine();
				Pattern regex = Pattern.compile(REGEX_FILE_CONTENT);
				Matcher match = regex.matcher(lines);
				if (match.find())
					isInjected = true;
			}
			reader.close();
		}
		return isInjected;
	}

	private final boolean ctypeChecker(Metadata metadata, String contentType) {
		boolean isTampered = true;
		String toCheck = null;
		if (metadata != null) {
			toCheck = metadata.get(metadata.CONTENT_TYPE);
		}
		if (toCheck.contains(contentType)) {
			isTampered = false;
		} else {
			isTampered = true;
		}
		return isTampered;
	}

}
