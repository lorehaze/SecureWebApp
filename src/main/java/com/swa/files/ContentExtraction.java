package com.swa.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

public class ContentExtraction {

	// regex pattern against LFI/XSS (escaped and unescaped)/SQLi
	public static final String REGEX_FILE_CONTENT_PATTERN = "(<script>|<\\/script>|\\.jsp|\\?[a-zA-Z]+=)";
	//public static final String REGEX_FILE_CONTENT_PATTERN = "(<script>|</script>)";
	
	public boolean FileChecker(InputStream file, String ContentType) throws IOException {;
		boolean flagType = false;
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
			System.out.println("TAMPER CHECK: " + flagType);
			break;
		case "image/":
			flagType = ctypeChecker(metadata, ContentType);
			break;
		}
		return flagType;
	}

	public final boolean fileVerify(File file) throws IOException {
		boolean isToDelete=false;
		
		isToDelete = regexChecker(file);
		
		System.out.println("IS TO DELETE?: " + isToDelete);
		
		/*if (isToDelete = true) {
			file.delete();
		}*/
		
		if (isToDelete) {
			return true;
		} else {
			return false;
		}
	}
	
	
	private final boolean regexChecker(File file) throws IOException {
		boolean isInjected = false;

		BufferedReader buff = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));

		String strCurrentLine = null;

		// check if file reaches function
		if (buff != null) {
			Pattern pattern = Pattern.compile(REGEX_FILE_CONTENT_PATTERN);
			// here i can read lines
			while ((strCurrentLine = buff.readLine()) != null) { //start while
				//System.out.println(strCurrentLine); // PRINT CURRENT LINE
				Matcher matcher = pattern.matcher(strCurrentLine);
				//System.out.println("MATCH: " + matcher.find());
				isInjected = matcher.find();
				System.out.println("INSIDE CYCLE " + isInjected);
				/*if (matcher.find()) {
					isInjected = true;
				}*/
			} //end while
		}
		
		/*if (isInjected = true) {
			file.delete();
		}*/
		
		System.out.println("REGEX FLAG: " + isInjected);
		
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
