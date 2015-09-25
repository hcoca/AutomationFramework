package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.TimeLinePanel;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author LuisCachi
 * 
 * This test case is to verify that after clicking on the Schedule panel, it redirects to the
 * Schedule page  
 */
public class VerifyTimeLineToMeetingsPage extends TestBaseSetup {
	// Page objects for this test
	private LoginPage login;
	private HomePage homePage;
	private MeetingsPage meetingsPage;
	
	
	// Room properties
	private String roomName;
	
	// Results
	private String expectedResult;
	private String actualResult;
	
 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
		LogManager.info("VerifyTimeLineToMeetingsPage: Executing Precondition, getting a default room");
 	}
 	
 	@Test
 	public void VerifyTimeLinegoToMeetingsPage(){
 		LogManager.info("VerifySchedulePanelAccessToMeetings: Executing Test Case");
 		
 		login = new LoginPage(driver);
 		homePage = login.access(roomName);
 		TimeLinePanel timeLp = new TimeLinePanel(homePage.getDriver());
 		meetingsPage = timeLp.clickOnMainPanel();
 		
 		expectedResult = "Schedule";
 		actualResult = meetingsPage.getScheduleLabelText();
 		

		Assert.assertEquals(actualResult, expectedResult);

 	}
}