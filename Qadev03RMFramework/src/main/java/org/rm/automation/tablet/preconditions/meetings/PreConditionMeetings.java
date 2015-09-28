package org.rm.automation.tablet.preconditions.meetings;

import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.utils.MeetingManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;

public class PreConditionMeetings {
	
	private static Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");

	private static String userPw = settings.getProperty("passwordES");
	
	private static String roomName;
	
	public static String getRoomName()
	{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		return roomName;
	}

	public static String createPastMeeting(String roomName, String meetingTitle) {
		try {
			MeetingManager.createPastMeeting(roomName, meetingTitle);
			return MeetingsRequests.getMeetingId(meetingTitle, roomName);
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static String getUserPassword(){
		return userPw;
	}
}
