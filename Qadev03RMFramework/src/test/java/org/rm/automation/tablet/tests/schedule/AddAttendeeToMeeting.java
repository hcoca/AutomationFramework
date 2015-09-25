package org.rm.automation.tablet.tests.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.MeetingsRequests;
import org.testng.annotations.*;

public class AddAttendeeToMeeting extends TestBaseSetup{

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
		
	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
 	}	
	
	@BeforeMethod
	public void CreateMeeting(){
		try{
			MeetingsRequests.postMeeting(roomName,title,startTime,endTime);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}
	
	@AfterMethod
	public void DeleteMeeting(){
		try{			
			String meetingId = MeetingsRequests.getMeetingId(title,roomName);
			MeetingsRequests.deleteMeeting(meetingId, roomName);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}
	
	@Test
	public void addAttendee(){
		LogManager.info("AddAttendeeToMeeting: Executing test case addAttendee");
		
		String attendee = "elmonito@roompro.com";	
		
		new LoginPage(driver)
		.access(url, username, password, roomName)
		.selectSchedulePage()
		
		//Meetings page functions
		.selectMeeting()
		.setAtendees(attendee)
		.confirmMeeting()		
		.confirmUser(password)
		.saveMeeting()		
		.attendeeAdded();
	}
} 