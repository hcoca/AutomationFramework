package org.rm.automation.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.parser.ParseException;
import org.rm.automation.utils.api.MeetingsRequests;

public class MeetingManager {
	
	// The value of a minute in milliseconds
	private static final long ONE_MINUTE_IN_MILLIS = 60000;
	private static final String END_OF_THE_DAY = "0:00";
	
	private static long meetingStartTime = 0;
	private static long meetingCreationTime = 0;
	private static long meetingEndTime = 0;
	private static Date date;
	
	public static void createRunninMeeting(String roomName, String meetingTitle, int behindMinute, int aheadMinute){
		date = new Date();
		long timeInMillisecond = date.getTime();
		setMeetingCreationTime(timeInMillisecond + ONE_MINUTE_IN_MILLIS);
		
		String startTime = RoomManagerTime.substractMinutesToCurrentTime(behindMinute);
		setMeetingStartTime(timeInMillisecond - (behindMinute * ONE_MINUTE_IN_MILLIS));
		
		String endTime = RoomManagerTime.addMinutesToCurrentTime(aheadMinute);
		setMeetingEndTime(timeInMillisecond + (aheadMinute * ONE_MINUTE_IN_MILLIS));
		
		try {
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTime, endTime);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getMeetingEndTimeFormated(){
		Date date = new Date(meetingEndTime);
		return RoomManagerTime.getHomePageTimeFormat(date);
	} 
	
	public static String getRemainingTimeFormated(){
		int timeDifference = (int) getRemainingTimeInMinutes();
		int minute = timeDifference%60;
		int hour = timeDifference/60;
		String formatedMinute = "";
		String formatedHour = "";
		
		if(hour < 10){
			formatedHour = "0" + String.valueOf(hour);
		}else{
			formatedHour = String.valueOf(hour);
		}
		
		if(minute < 10){
			formatedMinute = "0" + String.valueOf(minute);
		}else{
			formatedMinute = String.valueOf(minute);
		}
		
		return formatedHour + ":" + formatedMinute;
	}
	
	public static long getRemainingTimeInMinutes(){
		return Math.round((meetingEndTime - meetingCreationTime)/ONE_MINUTE_IN_MILLIS);
	}

	public static long getMeetingStartTime() {
		return meetingStartTime;
	}

	public static void setMeetingStartTime(long meetingStartTime) {
		MeetingManager.meetingStartTime = meetingStartTime;
	}

	public static long getMeetingCreationTime() {
		return meetingCreationTime;
	}

	public static void setMeetingCreationTime(long meetingCreationTime) {
		MeetingManager.meetingCreationTime = meetingCreationTime;
	}

	public static long getMeetingEndTime() {
		return meetingEndTime;
	}

	public static void setMeetingEndTime(long meetingEndTime) {
		MeetingManager.meetingEndTime = meetingEndTime;
	}
	
	public static String getEndOfTheDay(){
		return END_OF_THE_DAY;
	}
}
