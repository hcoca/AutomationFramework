package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.AvailablePanel;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;

/**
 * @author Pedro David Fuentes Antezana
 * 
 * This test case is to verify that when no scheduled meetings exists, the "available time" displayed
 * in the Available time panel is correct.  
 */
public class VerifyAvailableTimeWhenNoMeetingRuns extends TestBaseSetup {
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private AvailablePanel availablePanel;
	
	// Room properties
	private String roomName;
	
	// Results
	private String expectedResult;
	private String actualResult;
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		roomName = PreConditionHomePageTC.getRoomName();
 	}
 	
 	@Test
 	public void verifyAvailableTimeWhenNoMeetingRuns(){
 		
 		login = new LoginPage(driver);
 		homePage = login.access(roomName);
 		availablePanel = new AvailablePanel(homePage.getDriver());
 		availablePanel.waitForMainFreePanel(); // Check if it can goes in the constructor
 		
 		expectedResult = RoomManagerTime.getAvailableTimeTillEndOfDay();
 		actualResult = availablePanel.getAvailableTimeLeftText();

		Assert.assertEquals(actualResult, expectedResult);

 	}
}