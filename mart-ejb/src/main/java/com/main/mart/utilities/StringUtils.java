/**
 * 
 */
package com.main.mart.utilities;

/**
 * @author Hitesh
 *
 */
public class StringUtils {
	public static boolean isNullOrEmpty(String str) {
		boolean status = false;
		if(str == null || str == "" || str.isEmpty()) {
			status = true;
		}
		return status;
	}
}
