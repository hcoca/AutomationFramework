package org.rm.automation.tablet.tests.homepage;

import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.tablet.pageobjects.homepage.HomePage;
import org.rm.automation.tablet.pageobjects.homepage.NextHomePanel;
import org.rm.automation.tablet.preconditions.homepage.PreConditionHomePageTC;
import org.rm.automation.utils.TestBaseSetup;
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
		roomName = PreConditionHomePageTC.getRoomName();
	}
	
	@Test
	public void test(){		
		login = new LoginPage(driver);
 		homepage = login.access(roomName);
 		nextHomePage = new NextHomePanel(homepage.getDriver());
 		String actual = nextHomePage.getTitleNext();
		Assert.assertEquals(actual, expectedtitle);
	}

}
