package org.rm.automation.tablet.tests.homepage;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePanel;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * @author luiscachi
 *VerifyNextTitleWhenThereIsnotMeting
 */
public class VerifyNextTitleWhenThereIsnotMeting extends TestBaseSetup {
	
	private LoginPage login;
	private HomePage homepage;
	private NextHomePanel nextHomePage;
	String roomName;
	String expectedtitle = "End of day";
	
	
	@BeforeTest
	public void beforeclass(){		
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		roomName = allRooms.get(0).get("displayName").toString();
	}
	
	@Test
	public void test(){		
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePanel(homepage.getDriver());
 		String actual = nextHomePage.getTitleNext();
 		try {
			Assert.assertEquals(actual, expectedtitle);
		} catch (Throwable t) {
			LogManager.error("VerifyNextTitleWhenThereIsnotMeting assert is fail: "+t.toString());
		}
 		
	}

}
