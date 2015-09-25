package org.rm.automation.tablet.tests.homepage;


import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePanel;
import org.rm.automation.tablet.preconditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author luiscachi
 *VerifyNextTitleWhenThereisMeeting
 */
public class VerifyNextTitleWhenThereisMeeting extends TestBaseSetup {

	private LoginPage login;
	private HomePage homepage;
	private NextHomePanel nextHomePage;
	private String roomName;
	private String meetingTitle = "meetingTitle";
	private String meetingId;
	

	@BeforeTest
	public void beforeclass(){
		roomName = PreConditionHomePageTC.getRoomName();	
	}
	
	@Test
	public void test(){
		meetingId = PreConditionHomePageTC.createAfterMeeting();
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePanel(homepage.getDriver());
 		String actual = nextHomePage.getTitleNext();
		Assert.assertEquals(actual, meetingTitle);
	}
	
 	@AfterClass
 	public void tearDown(){
 		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
 	}
}