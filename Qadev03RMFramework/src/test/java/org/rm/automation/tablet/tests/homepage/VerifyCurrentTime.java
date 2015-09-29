package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;

import org.rm.automation.tablet.conditions.homepage.PreConditionHomePageTC;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import junit.framework.Assert;


/**
 * @author luiscachi
 * verify the current Time
 */
public class VerifyCurrentTime extends TestBaseSetup {

	private String roomName;

	@BeforeClass
	public void setup() throws UnsupportedOperationException, IOException {
		roomName = PreConditionHomePageTC.getRoomName();
	}

	@Test
	public void VerifyCurrentTimeInTabletHomePage() {
		
		String errorMessage = "The current time is diferent as we expect. ";
		String espectCurrent = RoomManagerTime.currenTime();
		LoginPage login = new LoginPage(driver);
		HomePage  homepage = login.access(roomName);
		String actualTime = homepage.currentTime();
	    Assert.assertTrue(errorMessage, actualTime.contains(espectCurrent));
	}
}
