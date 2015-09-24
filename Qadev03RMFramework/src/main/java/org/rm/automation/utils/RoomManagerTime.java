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
	
	public static String addMinutesToCurrentTime(int minutes){
		formatTime();
		
		long time = date.getTime();
		Date afterAddingMins = new Date(time + (minutes * ONE_MINUTE_IN_MILLIS));		
		return formatedTime.format(afterAddingMins);
	}
	
	public static String substractMinutesToCurrentTime(int minutes){
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
	
	public static String getHomePageTimeFormat(Date date){
		String res = "";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		res = simpleDateFormat.format(date).substring(0, 5);
		
		if(res.substring(0, 1).equals("0")){
			res = res.substring(1, 5);
		}
		
		return res;
	}
	
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
}
