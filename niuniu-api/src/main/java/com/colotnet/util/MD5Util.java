package com.colotnet.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5Util {

	private static MessageDigest MD5;
	static {
		try {
			MD5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取指定字节数组的MD5值，并以十六进制字符串形式返回
	 * @param msg
	 * @return
	 */
	public static synchronized String md5Hex(String msg,String charset) {
		try {
			return getMd5(msg.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	

	/**
	 * 获取指定字节数组的MD5值，并以十六进制字符串形式返回
	 * @param msg
	 * @return
	 */
	public static synchronized String getMd5(String msg) {
		return getMd5(msg.getBytes());
	}

	public static String getMd5(String source, String key){
		byte[] k_ipad = new byte[64];
		byte[] k_opad = new byte[64];
		byte[] keyb = null;
		byte[] value = null;
		try {
			keyb = key.getBytes("UTF8");
			value = source.getBytes("UTF8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
		Arrays.fill(k_ipad, keyb.length, 64, (byte)0x36);
		Arrays.fill(k_opad, keyb.length, 64, (byte)0x5c);
		
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5C);
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		md.update(k_ipad);
		md.update(value);
		byte[] dg = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return CodingUtil.bytesToHexString(dg);
	}

	/**
	 * 获取指定字符串的MD5值，并返回字节数组
	 * @param msg
	 * @return
	 */
	public static synchronized byte[] getMd5Byte(String msg) {
		return getMd5Byte(msg.getBytes());
	}

	/**
	 * 获取指定字节数组的MD5值，并返回字节数组
	 * @param msg
	 * @return
	 */
	public static synchronized byte[] getMd5Byte(byte[] msg) {
		return MD5.digest(msg);
	}

	/**
	 * 获取指定字节数组的MD5值，并返回十六进制的字符串
	 * @param msg
	 * @return
	 */
	public static synchronized String getMd5(byte[] msg) {
		MD5.update(msg);
		return CodingUtil.bytesToHexString(MD5.digest());
	}
}
