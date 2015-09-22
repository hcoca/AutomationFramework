package org.rm.automation.tablet.tests.search;

import java.util.Properties;

//import org.rm.automation.admin.pageobjects.HomePage;
//import org.rm.automation.admin.pageobjects.LoginPage;
//import org.rm.automation.admin.pageobjects.locations.IssuesPage;
//import org.rm.automation.admin.pageobjects.resources.AddResourcesPage;
//import org.rm.automation.admin.pageobjects.resources.ResourcesPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.StringGenerator;
import org.testng.annotations.Test;

public class VerifyRoomsDisplayed extends TestBaseSetup{
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	private String url = "http://" + settings.getProperty("server")+":"+
							settings.getProperty("port");
	
	private String name = StringGenerator.getString();
	private String displayName = StringGenerator.getString();
	private String iconName = "fa-gears";
	
	private LoginPage loginPage;
//	private HomePage homePage;
//	private ResourcesPage resourcesPage;
//	private AddResourcesPage addResourcesPage;
//	private IssuesPage issuesPage;
	
	@Test
	public void testSearchMeetingByRoom()
	{
		LogManager.info("SearchMeetingByRoom: Executing Test Case");

//		String roomName = "Morochata";
//		String url = "http://172.20.208.105:4040";
//		String username = "atxrm\\elver";
//		String password = "Control123";
//		BeginTest.getLogin()
//		.setUrl(url)
//		.setUsername(username)
//		.setPassword(password)
//		.login()
//		.setRoom()
//		.access()
//		.selectSearchPage()
//		.enableAdvancedSearch()
//		.typeRoomName(roomName)
//		.selectRoom(roomName)
//		.isTheRightRoom(roomName);
		
		new LoginPage(driver)
		.access(url, username, password, "RNY Room 1")
		.selectSearchPage()
		.verifyRoomsDisplayed();
		
//		loginPage = new LoginPage(driver);
//  		homePage = loginPage.SignIn(username, password);
//  		resourcesPage = homePage.SelectResourcesOption();		  		
//		addResourcesPage = resourcesPage.AddResource()
//				.setName(name)
//				.setDisplayName(displayName)
//				.setIcon(iconName);
//		resourcesPage =	addResourcesPage.Save();		
//		issuesPage = resourcesPage.SelectIssuesOption();
//		resourcesPage = issuesPage.SelectResourcesOption()
//				.VerifyResourceWasCreated(name, displayName, iconName);
//		resourcesPage.SignOut();
	}

}
