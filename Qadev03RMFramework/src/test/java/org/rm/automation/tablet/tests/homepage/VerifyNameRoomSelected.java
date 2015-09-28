package org.rm.automation.tablet.tests.homepage;

import java.io.IOException;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author luiscachi
 * VerifyNameRoomSelected
 */
public class VerifyNameRoomSelected extends TestBaseSetup {
	// import class needed
	private LoginPage login;
	private HomePage homepage;
	// room properties 
	private String roomName;

 	@BeforeClass
 	public void setup() throws UnsupportedOperationException, IOException{
 		roomName = PreConditionHomePageTC.getRoomName();
 	}
 	@Test
 	public void verifyNameRoom(){
 		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		String actual = homepage.getRoomNamelabel();

		Assert.assertEquals(actual, roomName);

 		
 	}
}
