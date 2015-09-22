package org.rm.automation.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class StringGenerator {
	
	private static SecureRandom random = new SecureRandom();
	static final String line = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdejfgijklmnopqrstuvwxyz";
	
	public static String getString() {
		int len = 8;
	    StringBuilder res = new StringBuilder(len);
	    for( int i = 0; i < len; i++ ) 
	       res.append( line.charAt( random.nextInt(line.length()) ) );
	    return res.toString();
	}
}


