package com.swa.crypt;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

import com.swa.dbconnection.Database;

public class PasswordHash { // this class uses salt + sha256 for the encoding

	public void clearArray(byte[] toClean) {
		for (int i = 0; i < toClean.length; i++) {
			toClean[i] = 0;
		}
	}

	private byte[] generateSalt(int n) {
		Random rd = new Random();
		byte[] salt = new byte[7];
		rd.nextBytes(salt);
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

	public boolean dbSalt(byte[] salt, int userID) {
		Connection con = Database.getConn_write();

		String sql = "INSERT INTO salt (salt_id,user_id,hash) VALUES (NULL,?,?)";

		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setBytes(2, salt);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clearArray(salt);
		if (i == 0) {
			return false;
		} else {
			return true;
		}
	}

	public byte[] saltPassword(byte[] pwd, int userID) {

		byte[] salt = generateSalt(pwd.length);
		System.out.println("SALT: "+salt);
		byte[] temp = appendArrays(pwd, salt);
		System.out.println("APPEND ARRAY: " +temp);

		
		byte[] hashVal = null;
		boolean flag = false;
		flag = dbSalt(salt, userID);
		System.out.println(flag);
		MessageDigest msgDigest;
		try {
			if (flag == true) {
				
				msgDigest = MessageDigest.getInstance("SHA-256");
				hashVal = msgDigest.digest(temp);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println("HASHVAL: " + hashVal);
		return hashVal;
	}

	public byte[] salter(byte[] pwd, byte[] salt) {
		byte[] salted = null;

		byte[] temp = appendArrays(pwd, salt);

		MessageDigest msgDigest;

		try {
			msgDigest = MessageDigest.getInstance("SHA-256");
			salted = msgDigest.digest(temp);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salted;
	}

	public byte[] getSalt(int userID) {
		byte[] salt = null;

		Connection con = Database.getConn_read();

		String sql = "SELECT `hash` FROM `salt` WHERE user_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				salt = rs.getBytes(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salt;
	}

}
