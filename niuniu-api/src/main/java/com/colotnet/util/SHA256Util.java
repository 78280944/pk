package com.colotnet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {

	private static MessageDigest SHA256;

	static {
		try {
			SHA256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static synchronized String getSha256(String msg) {
		return getSha256(msg.getBytes());
	}

	public static synchronized String getSha256(byte[] msg) {
		SHA256.update(msg);
		return CodingUtil.bytesToHexString(SHA256.digest());
	}

}
