package org.rm.automation.admin.tests.conferenceRooms;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.simple.JSONArray;
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
import org.rm.automation.admin.pageobjects.conferenceRooms.OutOfOrderPlanningPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.ResourceAssociationPage;
import org.rm.automation.admin.pageobjects.conferenceRooms.RoomInfoPage;
import org.rm.automation.utils.StringGenerator;
import org.rm.automation.utils.api.ConferenceRoomsRequests;
import org.rm.automation.utils.api.ResourcesRequests;

public class ThisTestCaseIsToVerifyThatXY {
	
	public static void main(String[] ar){
		LoginPage login;
		HomePage homePage;
		ConferenceRoomsPage conferenceRoom;
		RoomInfoPage roomInfo;
		ResourceAssociationPage resourceAssociationPage;
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:4040/admin");
		login = new LoginPage(driver);
		homePage = login.SignIn("571Network\\Administrator", "Pilot571david77");
		conferenceRoom = homePage.SelectRoomsOption();//
		System.out.println("The number of rooms is: " + conferenceRoom.getNumberOfRoomsFromLabel());
}
	
//	public static void testMainLUFER(String[] ar){
//		/*ArrayList<JSONObject> eg;
//		try {
//			eg = ConferenceRoomsRequests.getRooms();
//			for(JSONObject json : eg){
//				if(json.get("customDisplayName").toString().equals("room67")){
//					System.out.println(json);
//				}
//			}
//		} catch (UnsupportedOperationException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}*/
//		
//		LoginPage login;
//		HomePage homePage;
//		ConferenceRoomsPage conferenceRoom;
//		RoomInfoPage roomInfo;
//		
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.get("http://172.20.208.142:4040/admin");
//		login = new LoginPage(driver);
//		homePage = login.SignIn("roompro\\room", "Control123!");
//		conferenceRoom = homePage.SelectRoomsOption();
//		
//		roomInfo = conferenceRoom.doubleClickConferenceRoom("b21");
//		OutOfOrderPlanningPage ooop = roomInfo.clickOutOfOrderPlanningBtn();
//		
//
//		ooop.clickcalendarfrom();
//		ooop.clickbuttonToDayfrom();
//		
//		ooop.clickcalendatTo();
//		ooop.clickbuttonToDayTo();
//		
//		ooop.enableDisableBtn();
//		
//		ooop.setUPhorafrom(5);
//		ooop.setUpminutesFrom(15);
//		ooop.setUPhoraTo(5);
//		ooop.setUpminutesTo(5);
//		
//		ooop.setdownhoursFrom(6);
//		ooop.setDownMinuteFrom(5);
//		ooop.setdownhoursTo(5);
//		ooop.setDownMinuteTo(0);
//		
//		// value accepted is 
//		/*
//		 * Temporarily Out of Order (Order , Temporarily)
//		 * Closed for maintenance (maintenance)
//		 * Closed for reparations {reparations}
//		 * Reserved for a special event (Reserved , special)
//		 * if the name in invalid this select by default first option
//		*/
//		ooop.selectTitle("hola mundooooooooooooo");
//		
//		ooop.savebuttonOOOP();
//		
//	}
	
	
//	public static void main(String[] ar){
//		LoginPage login;
//		HomePage homePage;
//		ConferenceRoomsPage conferenceRoom;
//		RoomInfoPage roomInfo;
//		ResourceAssociationPage resourceAssociationPage;
//		
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.get("http://localhost:4040/admin");
//		login = new LoginPage(driver);
//		homePage = login.SignIn("571Network\\Administrator", "Pilot571david77");
//		conferenceRoom = homePage.SelectRoomsOption();//
//		roomInfo = conferenceRoom.doubleClickConferenceRoom("room571");
//		roomInfo = roomInfo.setCode("007");
//		conferenceRoom = roomInfo.clickSaveBtn();
//		boolean res = conferenceRoom.isCodeUpdated("007", "room571");
//		System.out.println("The result is: " + res);
//}
	
//	public static void main(String[] ar){
//		LoginPage login;
//		HomePage homePage;
//		ConferenceRoomsPage conferenceRoom;
//		RoomInfoPage roomInfo;
//		ResourceAssociationPage resourceAssociationPage;
//		
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.get("http://localhost:4040/admin");
//		login = new LoginPage(driver);
//		homePage = login.SignIn("571Network\\Administrator", "Pilot571david77");
//		conferenceRoom = homePage.SelectRoomsOption();// ng-hide="row.entity.enabled"
//		System.out.println("room571 is: " + conferenceRoom.isEnabledRoom("room571"));
//		System.out.println("room67 is: " + conferenceRoom.isEnabledRoom("room67"));
////		List<WebElement> list = conferenceRoom.roomsContainer.findElements(By.xpath("//span[@ng-show='row.entity.enabled']"));
////		for(WebElement we : list){
////			if(we.getText().equals("room67")){
////				System.out.println(we.getText() + " is " + true);
////			}else{
////				System.out.println(we.getText() + " is " + false);
////			}
////		}
//}
	
//	public static void main(String[] ar) throws UnsupportedOperationException, IOException{
//		String roomId = ConferenceRoomsRequests.getRooms().get(0).get("_id").toString();
//		System.out.println(ConferenceRoomsRequests.getRooms().get(0));
//		System.out.println(roomId);
//		
//		ArrayList<String> x = ConferenceRoomsRequests.getResourceIdAssociatedToRoom(roomId);
//		for(String s : x){
//			System.out.println(s);
//		}
//		
//	}
	
//	public static void main(String[] ar){
//		LoginPage login;
//		HomePage homePage;
//		ConferenceRoomsPage conferenceRoom;
//		RoomInfoPage roomInfo;
//		ResourceAssociationPage resourceAssociationPage;
//		
//		WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.get("http://localhost:4040/admin");
//		login = new LoginPage(driver);
//		homePage = login.SignIn("571Network\\Administrator", "Pilot571david77");
//		conferenceRoom = homePage.SelectRoomsOption();
//		
//		roomInfo = conferenceRoom.doubleClickConferenceRoom("room571");
//		resourceAssociationPage = roomInfo.clickResourceAssociationBtn();
//		
//		resourceAssociationPage.associateResource("Film");
//		resourceAssociationPage.clickSaveButton();
//		conferenceRoom.clickOnResource("Film");
//		
//		boolean res = conferenceRoom.isAssociatedToResource("Film", "room571");
//		System.out.println("IS ASSOCIATED: " + res);
//		try {
//			System.out.println(ConferenceRoomsRequests.getRooms().get(0));
//		} catch (UnsupportedOperationException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] ar){
//		try {
//			JSONObject jo = ConferenceRoomsRequests.getRooms().get(0);
//			String roomId = jo.get("_id").toString();
//			System.out.println(jo);
//			ConferenceRoomsRequests.setValue(roomId, "enabled", "false");
//			
//			JSONObject eg = ConferenceRoomsRequests.getRooms().get(0);
//			System.out.println(eg);
//			
//		} catch (UnsupportedOperationException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] ar){
//		ArrayList<JSONObject> eg;
//		try {
//			eg = ConferenceRoomsRequests.getRooms();
//			for(JSONObject json : eg){
//				if(json.get("customDisplayName").toString().equals("room67")){
//					System.out.println(json);
//				}
//			}
//		} catch (UnsupportedOperationException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
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
//		roomInfo.setCapacity("1098571");
//		
//		conferenceRoom = roomInfo.clickSaveBtn();
//		
//		try {
//			ArrayList<JSONObject> list = ConferenceRoomsRequests.getRooms();
//			for(JSONObject json : list){
//				if(json.get("customDisplayName").toString().equals("room67")){
//					System.out.println(json);
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
//			JSONObject jo = ConferenceRoomsRequests.getRooms().get(0);
//			System.out.println(jo);
//			
//		} catch (UnsupportedOperationException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String x = null;
//		System.out.println(x);
//	}
	
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
