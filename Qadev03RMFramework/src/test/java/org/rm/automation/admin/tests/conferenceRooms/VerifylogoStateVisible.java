package org.rm.automation.admin.tests.conferenceRooms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.OutOfOrderPlanningPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.RoomInfoPage;
import org.rm.automation.tablet.conditions.conferenceRooms.PreConditionConferenceRooms;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class VerifylogoStateVisible extends TestBaseSetup{
	private LoginPage login;
	private HomePage homePage;
	private ConferenceRoomsPage conferenceRoom;
	private RoomInfoPage roomInfo;
	private OutOfOrderPlanningPage ooop;
	
	// room properties 
	private String roomName;
	
	// login properties
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		roomName = PreConditionConferenceRooms.getRoomName();
 	}
	
 	
 	/// test not testing 
	@Test
	public void test() {
		/* Temporarily Out of Order       (Order , Temporarily)
		 * Closed for maintenance         (maintenance)
		 * Closed for reparations         (reparations)
		 * Reserved for a special event   (Reserved , special)*/
		String typeOOO = "maintenance";
		
		
		login = new LoginPage(driver);
		homePage = login.SignIn(userName, password);
		conferenceRoom = homePage.SelectRoomsOption();

		roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		ooop = roomInfo.clickOutOfOrderPlanningBtn();
		conferenceRoom = ooop.createOOOPactive(typeOOO);
		/// steps extra
		login =conferenceRoom.SignOut();
		login = new LoginPage(driver); 
		homePage = login.SignIn(userName, password);
		homePage.SelectResourcesOption();
		conferenceRoom = homePage.SelectRoomsOption();
		
		//assercions
		Boolean espected = conferenceRoom.IsvisibleOOOIcon(typeOOO);
		LogManager.warn("res is ====>" + espected);
		Assert.assertTrue(espected);
		
	}
}
