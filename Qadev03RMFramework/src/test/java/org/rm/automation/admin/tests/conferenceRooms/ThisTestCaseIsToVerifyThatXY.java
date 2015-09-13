package org.rm.automation.admin.tests.conferenceRooms;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.utils.api.ConferenceRoomsRequests;

import com.gargoylesoftware.htmlunit.javascript.host.Console;


public class ThisTestCaseIsToVerifyThatXY {
	
	
//	public static void main(String[] args) {
//		WebDriver driver = new FirefoxDriver();
//		RMLoginPage rml = new RMLoginPage(driver);
//		driver.get("http://localhost:4040/admin");
//		rml.setUserName("571Network\\Administrator")
//			.setPassword("Pilot571david77")
//			.clickSignInBtn()
//			.clickConferenceRoomBtn()
//			.doubleClickConferenceRoom()
//			.clickRoomInfoBtn()
//			.clickResourceAssociationBtn()
//			.clickOutOfOrderPlanningBtn();
//	}
	
//	public static void main(String[] args) {
//	WebDriver driver = new FirefoxDriver();
//	RMLoginPage rml = new RMLoginPage(driver);
//	driver.get("http://localhost:4040/admin");
//	rml.setUserName("571Network\\Administrator")
//		.setPassword("Pilot571david77")
//		.clickSignInBtn()
//		.clickConferenceRoomBtn()
//		.doubleClickConferenceRoom()
//		.clickPowerBtn()
//		.clickSaveBtn();
//	}
	
//	public static void main(String[] args) {
//	WebDriver driver = new FirefoxDriver();
//	LoginPage rml = new LoginPage(driver);
//	driver.get("http://localhost:4040/admin");
//	driver.manage().window().maximize();
//	rml.setUserName("roompro\\room")
//		.setPassword("Control123!")
//		.clickSignInBtn()
//		.clickConferenceRoomBtn()
//		.doubleClickConferenceRoom()
//		.clickPowerOnBtn()
//		.clickPowerOffBtn()
//		.clickSaveBtn();
//	}
	
	public static void main(String[] ar){
		WebDriver driver = new FirefoxDriver();
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.SignIn("571Network\\Administrator", "Pilot571david77");
		ConferenceRoomsPage conferenceRoom = homePage.SelectRoomsOption();
		System.out.println("The room's name using the UI: " + conferenceRoom.conferenceRoom.getText());
		try {
			ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
			System.out.println("The room's name using the API: " + allRooms.get(0).get("displayName"));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
