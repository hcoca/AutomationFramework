package org.rm.automation.admin.tests.conferenceRooms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * @author Pedro David Fuentes Antezana.
 * 
 * This test case is to verify that the room selected through GUI is the same as the one retrieved by API.	
 */
public class VerifyRoomName extends TestBaseSetup{
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	String userName = settings.getProperty("username");
	String password = settings.getProperty("password");

	private String expectedResult;
	private String actualResult;
	
 	@BeforeTest
 	public void setup() throws UnsupportedOperationException, IOException{
		ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
		expectedResult =  allRooms.get(0).get("displayName").toString();
 	}
	
	@Test(priority = 2)
	public void verifyRoomColumnBtn(){
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.SignIn(userName, password);
		ConferenceRoomsPage conferenceRoom = homePage.SelectRoomsOption();
		
		actualResult = conferenceRoom.conferenceRoom.getText();
		
		AssertJUnit.assertEquals(expectedResult, actualResult);
	}
}
