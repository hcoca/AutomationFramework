package org.rm.automation.admin.conditions.conferenceRooms;

import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.ResourcesRequests;

public class PostConditionConferenceRooms {

	public static void deleteResource(String resourceId) {
		ResourcesRequests.deleteResource(resourceId);
	}

	public static void setConferenceRoomCapacity(String roomId, String string, String roomCapacity) {
		ConferenceRoomsRequests.setValue(roomId, "capacity", roomCapacity);
	}

	public static void setConferenceRoomCode(String roomId, String string, String roomCode) {
		ConferenceRoomsRequests.setValue(roomId, "code", roomCode);
	}

	public static void setConferenceRoomName(String roomId, String roomName) {
		ConferenceRoomsRequests.putRoom(roomId, roomName);
	}

	public static void setEnabledStatus(String roomId, String enabledStatus) {
		ConferenceRoomsRequests.setValue(roomId, "enabled", enabledStatus);
	}
}
