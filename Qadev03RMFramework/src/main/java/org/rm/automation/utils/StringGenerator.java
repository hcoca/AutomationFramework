package org.rm.automation.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class StringGenerator {
	
	private static SecureRandom random = new SecureRandom();
	
	public static String getString() {
	    return new BigInteger(130, random).toString(32);
	}

//	public static void main(String[] args) {
//		
//		System.out.println(StringGenerator.nextSessionId());
//	}
			  
}


