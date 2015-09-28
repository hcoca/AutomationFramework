package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NowPanel;
import org.rm.automation.tablet.preconditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Pedro David Fuentes Antezana
 * 
 * This test case is to verify that after a meeting has been created, the meeting title is the same as
 * the title displayed in the "now panel" of the tablet home page.  
 */
public class VerifyTitleOnRunningMeeting extends TestBaseSetup {
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private NowPanel nowPanel;

	// Room properties
	private String roomName;
	
	// Meeting properties
	private String meetingTitle = "meetingTitle";
	private int behindMinute = 1; // 1 minute before current time
	private int aheadMinute = 3; // 3 minutes ahead current time
	private String meetingId;
	
	// Results
	private String expectedResult = meetingTitle;
	private String actualResult;
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
 		roomName = PreConditionHomePageTC.getRoomName();
 		meetingId = PreConditionHomePageTC.createCurrentMeeting(roomName, meetingTitle, behindMinute, aheadMinute);
 	}
 	
 	@Test
 	public void verifyTitleOnRunningMeeting(){
 		LogManager.info("VerifyTitleOnRunningMeeting: Executing Test Case");
 		
 		login = new LoginPage(driver);
 		homePage = login.access(roomName);
 		nowPanel = new NowPanel(homePage.getDriver());
 		nowPanel.waitForMainPanel(); // Check if it can goes in the constructor
 		actualResult = nowPanel.getTitleLabelText();
 		
		Assert.assertEquals(actualResult, expectedResult);
 	}
 	
 	@AfterClass
 	public void tearDown(){
 		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
 	}
}