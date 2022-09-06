package com.swa.session;

import javax.servlet.http.Cookie;

import com.swa.crypt.AES;

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
}
