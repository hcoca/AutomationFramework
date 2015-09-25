package org.rm.automation.tablet.preconditions.homepage;


import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;


public class PreConditionHomePageTC {

	private static String roomName;

	private static String meetingTitle = "meetingTitle";
	private static String startTimeAfter = RoomManagerTime.addMinutesToCurrentTime(15);
	private static String startTimeCurrent = RoomManagerTime.substractMinutesToCurrentTime(2);
	private static String endTime = RoomManagerTime.addMinutesToCurrentTime(16);

	//this method return the first room name
	public static String GetRoomName()
	{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		return roomName;
	}
	
	// this method create a meeting and return the meetings Id
	public static String CreateCurrentMeeting(){
		try {
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTimeCurrent, endTime);
			return MeetingsRequests.getMeetingId(meetingTitle, roomName);
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static String CreateAfterMeeting(){
		try {
			MeetingsRequests.postMeeting(roomName, meetingTitle, startTimeAfter, endTime);
			return MeetingsRequests.getMeetingId(meetingTitle, roomName);
		}catch(Exception e){
			
		}
		return null;
	}
}
