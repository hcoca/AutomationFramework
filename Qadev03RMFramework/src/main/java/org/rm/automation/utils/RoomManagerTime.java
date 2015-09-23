package org.rm.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RoomManagerTime {
	
	// The value of a minute in milliseconds
	private static final long ONE_MINUTE_IN_MILLIS = 60000;
	
	// The standard variables for using dates and date formats
	private static Date date;
	private static SimpleDateFormat formatedTime;
	
	/**
	 * @return a String with the actual current time with the Room Manager format. 
	 */
	public static String getRoomManagerTime(){
		formatTime();
		
		return formatedTime.format(date);
	}
	
	/**
	 * @param minutes
	 * @return a String with the number of minutes ahead of the current Room manager
	 * time format.
	 */
	public static String addMinutesToCurrentTime(int minutes){
		formatTime();
		
		long time = date.getTime();
		Date afterAddingMins = new Date(time + (minutes * ONE_MINUTE_IN_MILLIS));
		
		return formatedTime.format(afterAddingMins);
	}
	
	/**
	 * @param minutes
	 * @return a String with the number of minutes behind of the current Room manager
	 * time format.
	 */
	public static String substractMinutesToCurrentTime(int minutes){
		formatTime();
		
		long time = date.getTime();
		Date afterAddingMins = new Date(time - (minutes * ONE_MINUTE_IN_MILLIS));
		
		return formatedTime.format(afterAddingMins);
	}
	
	/**
	 * Private method that sets the GMT time zone and creates a format equal
	 * to the time format used in the Room Manager application.
	 */
	private static void formatTime(){
		date = new Date();
		formatedTime = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss'.000Z'");
		formatedTime.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
}
