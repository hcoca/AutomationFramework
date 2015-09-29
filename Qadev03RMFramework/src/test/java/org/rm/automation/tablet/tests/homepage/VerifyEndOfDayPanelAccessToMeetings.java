package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.EndOfDayPanel;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Pedro David Fuentes Antezana
 * 
 * This test case is to verify that after clicking on the End of day panel, it redirects to the
 * Schedule page  
 */
public class VerifyEndOfDayPanelAccessToMeetings extends TestBaseSetup {
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private EndOfDayPanel endOfDayPanel;
	private MeetingsPage meetingsPage;
	
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
 	public void verifyAvailablePanelAccesToMeetings(){
 		
 		login = new LoginPage(driver);
 		homePage = login.access(roomName);
 		endOfDayPanel = new EndOfDayPanel(homePage.getDriver());
 		meetingsPage = endOfDayPanel.clickOnMainPanel();
 		
 		expectedResult = "Schedule";
 		actualResult = meetingsPage.getScheduleLabelText();
 		

		Assert.assertEquals(actualResult, expectedResult);
 	}
}