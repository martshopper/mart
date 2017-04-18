/**
 * 
 */
package com.main.mart.utilities;

import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Hitesh
 *
 */
public class MartUtilities {
	private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
	private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33,
            (byte) 0x10, (byte) 0x12, };
	public static Date getCurrentDateTime() {
		return new Date();
	}
	public static String encrypt(String property) {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
	        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
	        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
	        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	        return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
		}catch (Exception e) {
			e.printStackTrace();
		}return property;
	}
	private static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
	public static String decrypt(String property) {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
	        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
	        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
	        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
		} return property;
	}
	private static byte[] base64Decode(String property) {
        try {
        	return Base64.decodeBase64(property);
        }catch (Exception e) {
			e.printStackTrace();
		}return null;	
    }
}
