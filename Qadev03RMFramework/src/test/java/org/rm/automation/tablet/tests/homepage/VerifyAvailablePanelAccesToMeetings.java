package org.rm.automation.tablet.tests.homepage;

import org.rm.automation.tablet.conditions.homepage.PreConditionHomePageTC;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.AvailablePanel;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * @author Pedro David Fuentes Antezana
 * 
 * This test case is to verify that after clicking on the Available panel, it redirects to the
 * Schedule page  
 */
public class VerifyAvailablePanelAccesToMeetings extends TestBaseSetup {
	
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private AvailablePanel availablePanel;
	private MeetingsPage meetingsPage;
	
	// Room properties
	private String roomName;
	
	// Results
	private String expectedResult;
	private String actualResult;
	
 	@BeforeClass
 	public void setup(){
 		roomName = PreConditionHomePageTC.getRoomName();
 	}
 	
 	@Test
 	public void verifyAvailablePanelAccesToMeetings(){ 		
 		login = new LoginPage(driver);
 		homePage = login.access(roomName);
 		availablePanel = new AvailablePanel(homePage.getDriver());
 		meetingsPage = availablePanel.clickOnMainFreePanel();
 		
 		expectedResult = "Schedule";
 		actualResult = meetingsPage.getScheduleLabelText();

		Assert.assertEquals(actualResult, expectedResult);
 	}
}