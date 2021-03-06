/**
 * 
 */
package com.main.mart.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.jboss.logging.Logger;

import com.main.mart.entity.User;

/**
 * @author Hitesh
 *
 */
public class MartUtilities {
	private static Integer user_id;
	private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
	private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33,
            (byte) 0x10, (byte) 0x12, };
	private static final String DATETIME_FORMAT_UI = "dd-MMM-yyyy HH:mm";
	private static final String DATE_FORMAT_UI = "dd-MMM-yyyy";
	private static final Logger logger = Logger.getLogger(MartUtilities.class);
	public static String cnvtDBDateToUIDate(Date dbDate) {
		try {
			if(dbDate != null) {
				DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_UI);
				return dateFormat.format(dbDate);
			}
		}catch (Exception e) {
			showErrorLog(e);
		}return null;
	}
	public static String cnvtDBDateTimeToUIDateTime(Date dbDate) {
		try {
			if(dbDate != null) {
				DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT_UI);
				return dateFormat.format(dbDate);
			}
		}catch (Exception e) {
			showErrorLog(e);
		}return null;
	}
	public static Date cnvtUIStringDateToDate(String uiDate) {
		if(uiDate== null || uiDate.isEmpty()) {
			return null;
		}
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_UI);
			return (Date)dateFormat.parse(uiDate);
		}catch (Exception e) {
			showErrorLog(e);
		} return null;
	}
	public static Date cnvtUIStringDateTimeToDate(String uiDate) {
		if(uiDate== null || uiDate.isEmpty()) {
			return null;
		}
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT_UI);
			return (Date)dateFormat.parse(uiDate);
		}catch (Exception e) {
			showErrorLog(e);
		} return null;
	}
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
			showErrorLog(e);
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
			showErrorLog(e);
		} return property;
	}
	private static byte[] base64Decode(String property) {
        try {
        	return Base64.decodeBase64(property);
        }catch (Exception e) {
        	showErrorLog(e);
		}return null;	
    }
	public static String getUserFullName(User user) {
		try {
			StringBuilder nameBuilder = new StringBuilder();
			if(user.getFirstName() != null) {
				nameBuilder.append(user.getFirstName());
			}
			if(user.getLastName() != null) {
				nameBuilder.append(" ").append(user.getLastName());
			}
			if(user.getMiddleName() != null) {
				nameBuilder.append(" ").append(user.getMiddleName());
			}
			return nameBuilder.toString();
		}catch (Exception e) {
			showErrorLog(e);
		}return null;
	}
	public static void showErrorLog(Exception e) {
		logger.error("Exception : "+e.getMessage());
		logger.error(e);
	}
	public static void showLog(Object e) {
		logger.info(e);
	}
	public static void setLoginUserId(Integer userId) {
		user_id = userId;
	}
	public static Integer getUserId() {
		return user_id;
	}
	
	public static String getCurrentYear() {
		try {
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			return ""+year;
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}
	
	public static java.util.Date getDateTimeMMMSSFromString(String str) {	

		if(str == null || str.isEmpty()) {
			return null;
		}
		Date date = null;
		DateFormat formatter ; 
		formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		try {
			date = (Date)formatter.parse(str);
		}
		catch(ParseException e) {
			date = null;
		}
		//           // logger.info("Date:"+date);
		return date;
	}
	
	public static java.util.Date getDateFromStringWithMMM(String str) {

		if (str == null || str.isEmpty()) {
			return null;
		}
		Date date = null;
		DateFormat formatter;
		formatter = new SimpleDateFormat(DATE_FORMAT_UI);
		try {
			date = (Date) formatter.parse(str);
		} catch (ParseException e) {
			date = null;
		}
		// logger.info("Date:" + date);
		return date;
	}
	
	public static String getStringDateInMMMFromDate(Date date) {	

		if(date == null) {
			return null;
		}
		SimpleDateFormat parseDatetoString;
		parseDatetoString = new SimpleDateFormat(DATE_FORMAT_UI);
		String strdate = parseDatetoString.format(date);

		return strdate;
	}
	
	public static String getMysqlOnlyDateMMMGUIToDb(String dt){
		try{
			SimpleDateFormat formatter, FORMATTER;
			formatter = new SimpleDateFormat(DATE_FORMAT_UI);
			String USDate=null;

			Date date = formatter.parse(dt);
			FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

			USDate=FORMATTER.format(date);

			return USDate;
		} catch (ParseException e){
			logger.error("There is an exception");
			return null;
		}
	}
	
	
}
