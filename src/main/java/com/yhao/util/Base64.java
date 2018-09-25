package com.yhao.util;

/**
 * A class to do base64 encoding and decoding
 * 
 * <pre>
 * Base64 is a system for representing raw byte data as Ascii
 * characters. Each 3 input bytes will translate into 4
 * base64 digits. Each base64 digit is represented by an Ascii
 * character. This table shows how bytes are converted to
 * base64 digits.
 *
 * base64 |           |           |           |           |
 * digits |6 5 4 3 2 1|6 5 4 3 2 1|6 5 4 3 2 1|6 5 4 3 2 1|
 *        -------------------------------------------------
 * input  |8 7 6 5 4 3 2 1|8 7 6 5 4 3 2 1|8 7 6 5 4 3 2 1|
 * bytes  |               |               |               |
 *
 * Base64 encoding always extends the input data to a multiple
 * of 24 bits by padding with zeros.
 *
 * Base64 is fully described in RFC 1521.
 *
 * This public domain code provided by Jonathan Knudsen
 * Published in "Java Cryptography", 1998 by
 * O'Reilly & Associates.
 * 
 * Base64的字符串由ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/共64个字符组成，末尾不足补=
 * </pre>
 * 
 * @author 刘斌华
 * @since 2016年05月10日
 * 
 */
public final class Base64 {

	public static final String CHARSET = "UTF-8";

	/**
	 * getChar
	 *
	 * encapsulates the translation from six bit quantity to base64 digit
	 */
	private static char getChar(int sixBit, boolean isUrlSafe) {

		if (sixBit >= 0 && sixBit <= 25) {
			return (char) ('A' + sixBit);
		}
		if (sixBit >= 26 && sixBit <= 51) {
			return (char) ('a' + (sixBit - 26));
		}
		if (sixBit >= 52 && sixBit <= 61) {
			return (char) ('0' + (sixBit - 52));
		}
		if (sixBit == 62) {
			if(isUrlSafe) {
				return '-';
			} else {
				return '+';
			}
		}
		if (sixBit == 63) {
			if(isUrlSafe) {
				return '_';
			} else {
				return '/';
			}
		}

		return '?';

	}

	/**
	 * encodeBlock
	 *
	 * creates 4 base64 digits from three bytes of input data. we use an
	 * integer, block, to hold the 24 bits of input data.
	 *
	 * @return An array of 4 characters
	 */
	private static char[] encodeBlock(byte[] raw, int offset, boolean isUrlSafe) {

		int block = 0;
		int slack = raw.length - offset - 1;
		int end = (slack >= 2) ? 2 : slack;
		for (int i = 0; i <= end; i++) {
			byte b = raw[offset + i];
			int neuter = (b < 0) ? b + 256 : b;
			block += neuter << (8 * (2 - i));
		}

		char[] base64 = new char[4];
		for (int i = 0; i < 4; i++) {
			int sixbit = (block >>> (6 * (3 - i))) & 0x3f;
			base64[i] = getChar(sixbit, isUrlSafe);
		}

		if (slack < 1)
			base64[2] = '=';
		if (slack < 2)
			base64[3] = '=';

		return base64;

	}

	/**
	 * intValue
	 *
	 * translates from base64 digits to their 6 bit value
	 */
	private static int getValue(char c) {

		if (c >= 'A' && c <= 'Z')
			return c - 'A';
		if (c >= 'a' && c <= 'z')
			return c - 'a' + 26;
		if (c >= '0' && c <= '9')
			return c - '0' + 52;
		if (c == '+' || c == '-')
			return 62;
		if (c == '/' || c == '_')
			return 63;
		if (c == '=')
			return 0;

		return -1;

	}

	/**
	 * encode
	 *
	 * coverts a byte array to a string populated with base64 digits. It steps
	 * through the byte array calling a helper methode for each block of three
	 * input bytes
	 *
	 * @param data The byte array to encode
	 * @return A string in base64 encoding
	 */
	public static String encode(byte[] data) {
		
		StringBuilder encoded = new StringBuilder();
		for (int i = 0; i < data.length; i += 3) {
			encoded.append(encodeBlock(data, i, false));
		}
		
		return encoded.toString();
		
	}

	/**
	 * <h3>进行URL Safe的Base64编码</h3>
	 *
	 * @param data
	 * @return
	 */
	public static String encodeUrl(byte[] data) {

		StringBuilder encoded = new StringBuilder();
		for (int i = 0; i < data.length; i += 3) {
			encoded.append(encodeBlock(data, i, true));
		}

		return encoded.toString();

	}

	/**
	 * decode
	 *
	 * convert a base64 string into an array of bytes.
	 *
	 * @param data
	 *            A String of base64 digits to decode.
	 * @return A byte array containing the decoded value of the base64 input
	 *         string
	 */
	public static byte[] decode(String data) {

		int pad = 0;
		for (int i = data.length() - 1; data.charAt(i) == '='; i--)
			pad++;

		int length = data.length() * 6 / 8 - pad;
		byte[] raw = new byte[length];
		int rawIndex = 0;
		for (int i = 0; i < data.length(); i += 4) {
			int block = (getValue(data.charAt(i)) << 18)
					+ (getValue(data.charAt(i + 1)) << 12)
					+ (getValue(data.charAt(i + 2)) << 6)
					+ (getValue(data.charAt(i + 3)));
			for (int j = 0; j < 3 && rawIndex + j < raw.length; j++)
				raw[rawIndex + j] = (byte) ((block >> (8 * (2 - j))) & 0xff);
			rawIndex += 3;
		}
		
		return raw;
		
	}

}