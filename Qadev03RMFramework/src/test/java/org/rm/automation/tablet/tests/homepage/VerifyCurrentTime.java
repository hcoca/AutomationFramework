package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import junit.framework.Assert;


/**
 * @author luiscachi
 * verify the current Time
 */
public class VerifyCurrentTime extends TestBaseSetup {

	private LoginPage login;
	private HomePage homepage;

	private String roomName;

	@BeforeClass
	public void setup() throws UnsupportedOperationException, IOException {
		roomName = PreConditionHomePageTC.getRoomName();
	}

	
	// TODO - Change  the name of the test method with the test case name.
	@Test
	public void VerifyCurrentTimeInTabletHomePage() {
		
		String errorMessage = "The current time is diferent as we expect. ";
		String espectCurrent = RoomManagerTime.currenTime();

		login = new LoginPage(driver);
		homepage = login.access(roomName);
		String actualTime = homepage.currentTime();
		
	    Assert.assertTrue(errorMessage, actualTime.contains(espectCurrent));
	}
}
