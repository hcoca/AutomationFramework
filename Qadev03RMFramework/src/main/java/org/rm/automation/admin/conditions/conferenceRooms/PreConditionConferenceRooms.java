package org.rm.automation.admin.conditions.conferenceRooms;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.ResourcesRequests;

public class PreConditionConferenceRooms {
	
	/**
	 * @return roomName
	 */
	public static String getRoomName(){
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		return allRooms.get(0).get("displayName").toString();
	}
	
	public static String getEmailAddress(){
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		return allRooms.get(0).get("emailAddress").toString();
	}
	
	public static String getRoomId(){
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		return allRooms.get(0).get("_id").toString();
	}
	
	public static String getRoomCode(){
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		return allRooms.get(0).get("code").toString();
	}
	
	public static String getRoomEnabledState(){
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		return allRooms.get(0).get("enabled").toString();
	}
	
	public static int getNumberOfRooms(){
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		return allRooms.size();
	}
	
	public static String getCapacity(){
		String roomCapacity = null;
		
		JSONObject room = ConferenceRoomsRequests.getRooms().get(0);
		if(room.get("capacity") != null){
			roomCapacity = room.get("capacity").toString();
		}
		
		return roomCapacity;
	}

	public static String postResource(String resourceName, String resourceCustomName, String resourceIcon,
			String resourceDescription) {
		ResourcesRequests.postResource(resourceName, resourceCustomName, resourceIcon, resourceDescription);
		return ResourcesRequests.getResourceId(resourceName);
	}

	public static void setEnabledStatus(String roomId, boolean enabledStatus) {
		ConferenceRoomsRequests.setValue(roomId, "enabled", String.valueOf(enabledStatus));
	}
}
