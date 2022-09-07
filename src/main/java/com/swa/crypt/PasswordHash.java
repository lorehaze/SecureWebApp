package com.swa.crypt;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordHash { // this class uses salt + sha256 for the encoding

	private void clearArray(byte[] toClean) {
		for (int i = 0; i < toClean.length; i++) {
			toClean[i] = 0;
		}
	}

	private byte[] generateSalt(int n) {
		Random rd = new Random();
		byte[] salt = new byte[7];
		rd.nextBytes(salt);
		System.out.println(salt);
		return salt;
	}

	private byte[] appendArrays(byte[] first, byte[] next) {
		byte[] appended = new byte[first.length + next.length];
		ByteBuffer buff = ByteBuffer.wrap(appended);
		buff.put(first);
		buff.put(next);
		byte[] combined = buff.array();
		return combined;
	}
	
	
}
