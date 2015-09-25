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

public class RemoveMeeting extends TestBaseSetup{

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
	private String meetingRemoved = "Meeting successfully removed";
	private String roomName;
	
	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
 	}	
	
	@BeforeMethod
	public void createMeeting(){
		try{
			MeetingsRequests.postMeeting(roomName,title,startTime,endTime);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}
	
	@Test
	public void removeMeeting(){
		LogManager.info("RemoveMeeting: Executing test case removeMeeting");
		String errorMessage = "The meeting has not been removed";				
		LoginPage login = new LoginPage(driver);
		HomePage home = login.access(url, username, password, roomName);
		MeetingsPage meetings = home.selectSchedulePage();
		
		//Meetings page functions
		meetings.selectMeeting();
		
		//A little workaround because there's an API bug		
		meetings.updateMeeting();
		meetings.confirmUser(password);
		meetings.saveMeeting();
		home = meetings.goHomePage();
		//A little workaround because there's an API bug
		
		meetings = home.selectSchedulePage();
		meetings.selectMeeting();
		meetings.deleteMeeting();
		meetings.confirmUser(password);
		meetings.saveDeleteMeeting();	
		
		Assert.assertTrue(errorMessage, meetingRemoved.equals(meetings.meetingAdvices()));
		
	}
	
	
}
