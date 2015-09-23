package org.rm.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RoomManagerTime {
	
	private static final long ONE_MINUTE_IN_MILLIS = 60000; //millisecs
	
	private static Date date;
	private static SimpleDateFormat formatedTime;
	
	public static String getRoomManagerTime(){
		formatTime();
		
		return formatedTime.format(date);
	}
	
	public static String addMinutes(int minutes){
		formatTime();
		
		long time = date.getTime();
		Date afterAddingMins = new Date(time + (minutes * ONE_MINUTE_IN_MILLIS));
		
		return formatedTime.format(afterAddingMins);
	}
	
	public static String substractMinutes(int minutes){
		formatTime();
		
		long time = date.getTime();
		Date afterAddingMins = new Date(time - (minutes * ONE_MINUTE_IN_MILLIS));
		
		return formatedTime.format(afterAddingMins);
	}
	
	private static void formatTime(){
		date = new Date();
		formatedTime = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss'.000Z'");
		formatedTime.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
}
