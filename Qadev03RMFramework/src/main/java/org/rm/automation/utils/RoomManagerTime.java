package org.rm.automation.utils;

import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
//import com.gargoylesoftware.htmlunit.javascript.host.intl.DateTimeFormat;



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
	
	public static String getHomePageTimeFormat(Date date){
		String res = "";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		res = simpleDateFormat.format(date).substring(0, 5);
		
		if(res.substring(0, 1).equals("0")){
			res = res.substring(1, 5);
		}
		
		return res;
	}
	
	/**
	 * @return a time value without "0" in front of an hour when the hour
	 * is greater than 9.
	 */
	public static String getAvailableTimeTillEndOfDay(){
		String res = "";
		
		String currentTime = getHomePageTimeFormat(new Date());
		
		int currentHour = 0;
		int currentMinute = 0;
		
		if(currentTime.length() == 4){
			currentHour = Integer.parseInt(currentTime.substring(0, 1));
			currentMinute = Integer.parseInt(currentTime.substring(2));
		}else{
			currentHour = Integer.parseInt(currentTime.substring(0, 2));
			currentMinute = Integer.parseInt(currentTime.substring(3));
		}
		
		currentHour = 23 - currentHour;
		currentMinute = 59 - currentMinute;
		
		res = currentHour + ":" + currentMinute;
		
		return res;
	}

	public static String currenTime(){
		Date currentTime = new Date();
		return getHomePageTimeFormat(currentTime);
	}
	
	/*
	 * return the current time of the computer plus minutes added
	 * */
	public static String addminutesCurrentTime(int minutes){
		long time ;
		Date nd = new Date();
		time = nd.getTime();
		Date afterAddingMins = new Date(time + (minutes * ONE_MINUTE_IN_MILLIS));
		return getHomePageTimeFormat(afterAddingMins);
	}
	public static String addMinutesToDate(int minutes) {  
		Date myDate = new Date();
		Calendar calendarDate = Calendar.getInstance();  
	    calendarDate.setTime(myDate);  
	    calendarDate.add(Calendar.MINUTE, minutes); 
	    String time = new SimpleDateFormat("HH:mm").format(calendarDate.getTime());
	    return time;  
	}

}