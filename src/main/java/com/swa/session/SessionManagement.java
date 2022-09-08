package com.swa.session;

import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.swa.crypt.AES;
import com.swa.session.SessionManagement;

public class SessionManagement {

	public String SessionToken(String email) {

		AES cypher = new AES();

		String sessionToken = null;

		RandomString randstr = new RandomString(8);

		String mailPart = email.substring(0, 4);

		String temp = (new StringBuilder()).append(randstr).append(mailPart).toString();

		String temp_sessionToken = temp.substring(temp.lastIndexOf("@") + 1, temp.length()); // take only the string

		final String key = email;

		sessionToken = cypher.encrypt(temp_sessionToken, key);

		return sessionToken;
	}

	public boolean CheckSession(Cookie[] cookies) throws IOException {

		AES cypher = new AES();
		boolean isValidated = false;
		String email = null;
		String sessionToken = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equals("sessionToken")) {
					sessionToken = cookie.getValue();
				}
			}

			if (email != null) {
				if (sessionToken != null) {
					String temp = cypher.decrypt(sessionToken, email);
					String cmprEmail = email.substring(0, 4);
					String cmprSessionToken = temp.substring(temp.length() - 4);
					if (cmprEmail.equals(cmprSessionToken)) {
						isValidated = true;
					}
				}
			}
		}
		return isValidated;
	}

	public String retrieveEmail(Cookie[] cookies) {
		String email = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("email")) {
					email = cookie.getValue();
				}
			}
		}
		return email;
	}
}
