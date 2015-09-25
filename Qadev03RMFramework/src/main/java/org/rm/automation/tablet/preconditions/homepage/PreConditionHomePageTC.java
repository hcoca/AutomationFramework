package org.rm.automation.tablet.preconditions.homepage;


import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;


/**
 * @author luiscachi
 *
 */
public class PreConditionHomePageTC {

	private static String roomName;

	private static String meetingTitle = "meetingTitle";
	private static String startTimeAfter = RoomManagerTime.addMinutesToCurrentTime(15);
	private static String startTimeCurrent = RoomManagerTime.substractMinutesToCurrentTime(2);
	private static String endTime = RoomManagerTime.addMinutesToCurrentTime(16);

	
	/**
	 * @return roomName
	 */
	public static String getRoomName()
	{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		return roomName;
	}
	
	/**
	 * @return meeting ID
	 * create a meeting in current time
	 */
	public static String createCurrentMeeting(){
		try {
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTimeCurrent, endTime);
			return MeetingsRequests.getMeetingId(meetingTitle, roomName);
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static String createCurrentMeeting(String meetingTitle, int behindMinute, int aheadMinute) {
		try {
			String startTime = RoomManagerTime.substractMinutesToCurrentTime(behindMinute);
			String endTime = RoomManagerTime.addMinutesToCurrentTime(aheadMinute);
			
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTime, endTime);
			
			return MeetingsRequests.getMeetingId(meetingTitle, roomName);
		}catch(Exception e){
			
		}
		return null;
	}
	
	/**
	 * @return the meetingID
	 * create a meeting after the current time
	 */
	public static String createAfterMeeting(){
		try {
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTimeAfter, endTime);
			return MeetingsRequests.getMeetingId(meetingTitle, roomName);
		}catch(Exception e){
			
		}
		return null;
	}
	
	
	/**
	 * @param meetingTitle
	 * @param startTime in format RM
	 * @param endTime in format RM
	 * @return MeetingId of the setup meeting
	 */
	public static String setupMeeting(String meetingTitle , String startTime , String endTime){
		try {			
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTime, endTime);
			return MeetingsRequests.getMeetingId(meetingTitle, roomName);
		}catch(Exception e){	
		}
		return null;
	}
}
