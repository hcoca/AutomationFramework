package org.rm.automation.tablet.tests.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;
import org.testng.annotations.*;

import junit.framework.Assert;

public class RemoveAttendeeFromMeeting extends TestBaseSetup{

	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");	
	private String password = settings.getProperty("password");
	private String server = settings.getProperty("server");
	private String port = settings.getProperty("port");
	private String url = "http://"+server+":"+port;
	private String startTime = RoomManagerTime.substractMinutesToCurrentTime(1);
	private String endTime = RoomManagerTime.addMinutesToCurrentTime(3);
	private String title = "La vaca que sonrie";
	private String roomName;
	private String[] attendees = {"user001@atxrm.com"};
	
	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
 	}
	@BeforeMethod
	public void createMeeting(){
		try{
			MeetingsRequests.postMeeting(roomName,title,startTime,endTime,attendees);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}
	
	@AfterMethod
	public void deleteMeeting(){
		try{			
			String meetingId = MeetingsRequests.getMeetingId(title,roomName);
			MeetingsRequests.deleteMeeting(meetingId, roomName);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}
	
	@Test
	public void removeAttendee(){			
		LogManager.info("RemoveAttendeeFromMeeting: Executing test case removeAttendee");
		
		String errorMessage = "The attendee "+attendees[0]+" has not been removed";
		LoginPage login = new LoginPage(driver);
		HomePage home = login.access(url, username, password, roomName);
		MeetingsPage meetings = home.selectSchedulePage();
		meetings.selectMeeting();
		meetings.removeAttendee();
		meetings.confirmMeeting();
		meetings.confirmUser(password);
		meetings.saveMeeting();
		
		Assert.assertTrue(errorMessage,meetings.attendeeRemoved().isEmpty());;
	}
}
