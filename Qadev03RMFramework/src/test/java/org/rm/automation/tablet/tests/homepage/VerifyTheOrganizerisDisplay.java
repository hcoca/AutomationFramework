package org.rm.automation.tablet.tests.homepage;

import java.util.Properties;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePanel;
import org.rm.automation.tablet.preconditions.homepage.PostContidionHomePageTC;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * @author luiscachi
 * VerifyTheOrganizerisDisplay
 */
public class VerifyTheOrganizerisDisplay extends TestBaseSetup  {
	
	private LoginPage login;
	private HomePage homepage;
	private NextHomePanel nextHomePage;
	private String roomName;
	private String meetingId;
	
	
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userNameSpect = settings.getProperty("userES");
	
	@BeforeClass
	public void beforeclass(){
		roomName = PreConditionHomePageTC.getRoomName();
		meetingId = PreConditionHomePageTC.createAfterMeeting();
		if(meetingId == null)
		{
			LogManager.error("VerifyTheOrganizerisDisplay: "+ "before class fail the meeting is not create");
		}
	}
	
	@Test
	public void TheOrganizerisDisplayNextTitle(){
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePanel(homepage.getDriver());
 		String actual = nextHomePage.getOrganizer();
		Assert.assertEquals(actual, userNameSpect);
	}
	
 	@AfterClass
 	public void tearDown(){
 		PostContidionHomePageTC.deleteMeeting(meetingId, roomName);
 	}
}
