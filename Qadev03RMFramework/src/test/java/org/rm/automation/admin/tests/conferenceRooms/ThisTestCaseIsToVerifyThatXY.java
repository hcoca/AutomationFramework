package org.rm.automation.admin.tests.conferenceRooms;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.RoomInfoPage;
import org.rm.automation.utils.api.ConferenceRoomsRequests;

public class ThisTestCaseIsToVerifyThatXY {
	
	public static void main(String[] ar){
		try {
			JSONObject jo = ConferenceRoomsRequests.getRoom(ConferenceRoomsRequests.getRooms().get(0).get("_id").toString());
			System.out.println(jo);
			
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
//	public static void main(String[] ar){
//		try {
//			JSONObject jo = ConferenceRoomsRequests.getRooms().get(0);
//			System.out.println(jo);
//			ConferenceRoomsRequests.setValue(jo.get("_id").toString(), "code", "");
//			//ConferenceRoomsRequests.putRoom(jo.get("_id").toString(), "eg");
//			JSONObject eg = ConferenceRoomsRequests.getRooms().get(0);
//			System.out.println(eg);
//			
//		} catch (UnsupportedOperationException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
}
	
//	public static void main(String[] ar){
//		LoginPage login;
//		HomePage homePage;
//		ConferenceRoomsPage conferenceRoom;
//		RoomInfoPage roomInfo;
//		
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.get("http://localhost:4040/admin");
//		login = new LoginPage(driver);
//		homePage = login.SignIn("571Network\\Administrator", "Pilot571david77");
//		conferenceRoom = homePage.SelectRoomsOption();
//		
//		roomInfo = conferenceRoom.doubleClickConferenceRoom("room67");
//		roomInfo.setCode("1234");
//		
//		conferenceRoom = roomInfo.clickSaveBtn();
//		
//		try {
//			ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
//			for(JSONObject json : list){
//				if(json.get("customDisplayName").toString().equals("room67")){
//					System.out.println("The code is: " + json.get("code"));
//				}
//			}
//			
//		} catch (UnsupportedOperationException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] ar){
//		try {
//			ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
//			for(JSONObject json : list){
//				System.out.println(json);
//			}
//			
//		} catch (UnsupportedOperationException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] ar){
//		LoginPage login;
//		HomePage homePage;
//		ConferenceRoomsPage conferenceRoom;
//		RoomInfoPage roomInfo;
//		
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.get("http://localhost:4040/admin");
//		login = new LoginPage(driver);
//		homePage = login.SignIn("571Network\\Administrator", "Pilot571david77");
//		conferenceRoom = homePage.SelectRoomsOption();
//		
//		roomInfo = conferenceRoom.doubleClickConferenceRoom("room67");
//		roomInfo.setDisplayName("updatedRoom");
//		conferenceRoom = roomInfo.clickSaveBtn();
//		boolean res = conferenceRoom.isValidRoom("updatedRoom");
//		System.out.println("The updated room is:" + res);
//	}
	
//	public static void main(String[] ar){
//		try {
//			ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
//			String roomId = allRooms.get(0).get("_id").toString();
//			System.out.println("Before update: " + ConferenceRoomsRequests.getRooms().get(0).get("customDisplayName").toString());
//			ConferenceRoomsRequests.putRoom(roomId, "Hola");
//			System.out.println("After update: " + ConferenceRoomsRequests.getRooms().get(0).get("customDisplayName").toString());
//		} catch (UnsupportedOperationException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] ar){
//		WebDriver driver = new FirefoxDriver();
//		driver.get("http://localhost:4040/admin");
//		LoginPage login = new LoginPage(driver);
//		HomePage homePage = login.SignIn("571Network\\Administrator", "Pilot571david77");
//		ConferenceRoomsPage conferenceRoom = homePage.SelectRoomsOption();
//		
//		List<String> eg = conferenceRoom.listOfRoomsNames;
//		
//		for(String s : eg){
//			System.out.println(s);
//		}
//		
//		System.out.println("Contains room571: " + conferenceRoom.isValidRoom("room571"));
//	}

//	public static void main(String[] ar){
//		WebDriver driver = new FirefoxDriver();
//		driver.get("http://localhost:4040/admin");
//		LoginPage login = new LoginPage(driver);
//		HomePage homePage = login.SignIn("571Network\\Administrator", "Pilot571david77");
//		ConferenceRoomsPage conferenceRoom = homePage.SelectRoomsOption();
//		
//		WebElement rc = conferenceRoom.theRoomsContainer;
//		
//		(new WebDriverWait(driver, 20))
//		.until(ExpectedConditions.visibilityOf(rc));
//		
//		List<WebElement> list = rc.findElements(By.xpath("div"));
//		
//		for(WebElement div : list){
//			System.out.println("EG" + div.getText() + "++++++");
//			System.out.println("EG" + div.getText().trim() + "++++++");
//		}
//		
//		try {
//			ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
//			for(JSONObject json : allRooms){
//				System.out.println("EG" + json.get("displayName") + "without toString");
//				System.out.println("EG" + json.get("displayName").toString().trim() + "++++++");
//			}
//		} catch (UnsupportedOperationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//	}
	
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
//		WebDriver driver = new FirefoxDriver();
//		RMLoginPage rml = new RMLoginPage(driver);
//		driver.get("http://localhost:4040/admin");
//		rml.setUserName("571Network\\Administrator")
//			.setPassword("Pilot571david77")
//			.clickSignInBtn()
//			.clickConferenceRoomBtn()
//			.doubleClickConferenceRoom()
//			.clickPowerBtn()
//			.clickSaveBtn();
//	}
	
//	public static void main(String[] args) {
//		WebDriver driver = new FirefoxDriver();
//		LoginPage login = new LoginPage(driver);
//		
//		login.SignIn("571Network\\Administrator", "Pilot571david77")
//			.SelectRoomsOption()
//			.doubleClickConferenceRoom()
//			.clickPowerOnBtn()
//			.clickPowerOffBtn()
//			.clickSaveBtn();
//	}
	
//	public static void main(String[] ar){
//		WebDriver driver = new FirefoxDriver();
//		LoginPage loginPage = new LoginPage(driver);
//		HomePage homePage = loginPage.SignIn("571Network\\Administrator", "Pilot571david77");
//		ConferenceRoomsPage conferenceRoom = homePage.SelectRoomsOption();
//		
//		System.out.println("The room's name using the UI: " + conferenceRoom.conferenceRoom.getText());
//		
//		try {
//			ArrayList<JSONObject> allRooms = ConferenceRoomsRequests.getRooms();
//			System.out.println("The room's name using the API: " + allRooms.get(0).get("displayName"));
//		} catch (UnsupportedOperationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//	}
}
