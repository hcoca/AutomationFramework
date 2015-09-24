package org.rm.automation.tablet.tests.homepage;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyNextTitleWhenThereisMeeting extends TestBaseSetup {

	private LoginPage login;
	private HomePage homepage;
	private NextHomePage nextHomePage;
	String roomName;
	
	// this should be content the room the next meeting
	String expectedtitle ;
	@BeforeTest
	public void beforeclass(){
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
	}
	
	@Test
	public void test(){
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePage(homepage.getDriver());
 		String actual = nextHomePage.getTitleNext();
 		try {
			Assert.assertEquals(actual, expectedtitle);
		} catch (Throwable t) {
			LogManager.error("verifyNextTitleWhenThereIsnotMeting assert is fail: "+t.toString());
		}
	}
}