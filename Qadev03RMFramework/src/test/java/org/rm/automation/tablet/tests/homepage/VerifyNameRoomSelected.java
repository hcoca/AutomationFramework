package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author luiscachi
 * VerifyNameRoomSelected
 */
public class VerifyNameRoomSelected extends TestBaseSetup {
	// import class needed
	private LoginPage login;
	private HomePage homepage;
	// room properties 
	private String roomName;

 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
 	}
 	@Test
 	public void verifyNameRoom(){
 		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		String actual = homepage.getRoomNamelabel();
 		try {
 			Assert.assertEquals(actual, roomName);
		} catch (Throwable t) {
			LogManager.error("verify the name room -the assertion is failed " + t.toString() );
		}
 		
 	}
}
