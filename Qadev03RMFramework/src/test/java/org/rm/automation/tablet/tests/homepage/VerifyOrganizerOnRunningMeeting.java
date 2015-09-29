package org.rm.automation.tablet.tests.homepage;

import java.util.Properties;

import org.rm.automation.tablet.conditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.conditions.homepage.PreConditionHomePageTC;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NowPanel;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Pedro David Fuentes Antezana
 * 
 * This test case is to verify that after a meeting has been created, the meeting organizer is the same as
 * the organizer displayed in the "now panel" of the tablet home page.  
 */
public class VerifyOrganizerOnRunningMeeting extends TestBaseSetup {
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private NowPanel nowPanel;
	
	// Custom user environment settings
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	
	// Room properties
	private String roomName;
	
	// Meeting properties
	private String meetingTitle = "meetingTitle";
	private int behindMinute = 1; // 1 minute before current time
	private int aheadMinute = 3; // 3 minutes ahead current time
	private String meetingId;
	private String meetingOrganizer = settings.getProperty("userES");
	
	// Results
	private String expectedResult = meetingOrganizer;
	private String actualResult;
	
 	@BeforeClass
 	public void setup(){
 		roomName = PreConditionHomePageTC.getRoomName();
 		meetingId = PreConditionHomePageTC.createCurrentMeeting(roomName, meetingTitle, behindMinute, aheadMinute);
 	}
 	
 	@Test
 	public void verifyOrganizerOnRunningMeeting(){
 		LogManager.info("VerifyOrganizerOnRunningMeeting: Executing Test Case");
 		
 		login = new LoginPage(driver);
 		homePage = login.access(roomName);
 		nowPanel = new NowPanel(homePage.getDriver());
 		nowPanel.waitForMainPanel();
 		
 		actualResult = nowPanel.getOrganizerLabelText();
 		
		Assert.assertEquals(actualResult, expectedResult);

 	}
 	
 	@AfterClass
 	public void tearDown(){
 		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
 	}
}