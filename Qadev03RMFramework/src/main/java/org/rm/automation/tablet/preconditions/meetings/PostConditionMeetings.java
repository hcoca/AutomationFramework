package org.rm.automation.tablet.preconditions.meetings;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.api.MeetingsRequests;

public class PostConditionMeetings {
	
	/**
	 * @param meetingId
	 * @param roomName
	 * method Delete a meeting
	 */
	public static void deleteMeeting(String meetingId , String roomName){
		try {
			MeetingsRequests.deleteMeeting(meetingId, roomName);
			LogManager.info("VerifyNextTitleWhenThereisMeeting: Executing Postcondition, removing meeting");
		} catch (UnsupportedOperationException e) {
			LogManager.error("VerifyNextTitleWhenThereisMeeting: UnsupportedOperationException - " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			LogManager.error("VerifyNextTitleWhenThereisMeeting: IOException - " + e.toString());
			e.printStackTrace();
		} catch (ParseException e) {
			LogManager.error("VerifyNextTitleWhenThereisMeeting: ParseException - " + e.toString());
			e.printStackTrace();
		}
	}
}
