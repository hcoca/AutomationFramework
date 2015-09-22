package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;

import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.HomePage;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyNameRoomSelected extends TestBaseSetup {
	// import class needed
	LoginPage login = new LoginPage(driver);
	HomePage homepage;
	// tablet properties
	private String serviceURL;
	// room properties 
	private String roomName;

	
	// login properties
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String userPw = settings.getProperty("passwordES");
	private String server = settings.getProperty("server");
	private String port = settings.getProperty("port");
	
	
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		serviceURL = "http://"+server+":"+port;
 	}
 	@Test
 	public void verifyNameRoom(){
 		homepage = login.access(serviceURL, userName, userPw, roomName);
 		String actual = homepage.getRoomNamelabel();
 		try {
 			Assert.assertEquals(actual, roomName);
		} catch (Throwable t) {
			LogManager.error("verify the name room -the assertion is failed " + t.toString() );
		}
 		
 	}
}
