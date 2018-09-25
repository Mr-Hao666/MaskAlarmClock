package com.yhao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要算法
 *
 * @author 刘斌华
 * @since 2016年04月06日
 */
public final class Digest {

	private static final Logger logger = LoggerFactory.getLogger(Digest.class);

	/**
	 * <h3>MD5签名</h3>
	 *
	 * @param string
	 * @return 小写字母
	 */
	public static String md5(String string) {

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(string.getBytes(Charset.forName("UTF-8")));

			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : digest.digest()) {
				String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException ex) {
			logger.error("Exception occurred while getting signature with md5.", ex);
		}

		return "";

	}

	/**
	 * <h3>SHA签名</h3>
	 *
	 * @param string
	 * @return 小写字母
	 */
	public static String sha(String string) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(string.getBytes(Charset.forName("UTF-8")));

			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : digest.digest()) {
				String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException ex) {
			logger.error("Exception occurred while getting signature with sha.", ex);
		}

		return "";

	}

	/**
	 * <h3>SHA1签名</h3>
	 *
	 * @param string
	 * @return 小写字母
	 */
	public static String sha1(String string) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(string.getBytes(Charset.forName("UTF-8")));

			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : digest.digest()) {
				String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException ex) {
			logger.error("Exception occurred while getting signature with sha1.", ex);
		}

		return "";

	}

	public static byte[] hmacSha1Byte(String data, String key) {

		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(Charset.forName("UTF-8")), "HmacSHA1");
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);

			return mac.doFinal(data.getBytes(Charset.forName("UTF-8")));
		} catch (NoSuchAlgorithmException | InvalidKeyException ex) {
			logger.error("Exception occurred while getting signature with hmac-sha1.", ex);
		}

		return null;

	}

}
