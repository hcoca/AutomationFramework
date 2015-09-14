package org.rm.automation.tablet.tests.meetings;

import org.rm.automation.tablet.pageobjects.LoginPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.base.TestBaseSetup;
import org.testng.annotations.*;

public class CreateMeeting extends TestBaseSetup {
	/*@BeforeClass
	public void setUp() throws Exception {
		driver=myWebDriver.myDriver;
	}	*/	
	
	@Test
	public void createMeeting(){
		LogManager.info("Executing: Create a new meeting test case");
		String url = "http://172.20.208.105:4040";
		String username = "atxrm\\elver";
		String password = "Control123";
		String organizer = "Administrator";
		String subject = "PageObjects Meeting";
		String atendees = "elver@atxrm.com";
		String beginTime = "12:45";
		String endTime = "13:45";
		
		new LoginPage(driver)
		.access(url, username, password)
		.selectSchedulePage()
		.setOrganizer(organizer)
		.setSubject(subject)
		.setDates(beginTime, endTime)
		.setAtendees(atendees)
		.confirmMeeting()
		.confirmUser(password)
		.saveMeeting()
		.goHomePage();		
	}
}