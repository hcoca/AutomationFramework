package org.rm.automation.admin.tests.locations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.rm.automation.base.MyWebDriver;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.admin.pageobjects.LoginPage;

public class createBasicLocation extends TestBaseSetup{
	public static MyWebDriver myWebDriver;
	private WebDriver driver;
	private LoginPage loginPage;

  @BeforeClass
  public void setUp() throws Exception {
	  driver=myWebDriver.myDriver;
  }

  @Test
  public void testCreateBasicLocation() throws Exception {
	  String userName = "qadev03\\userRM01";
	  String password = "Control123!";
	  String name = "Location 1";
	  String displayName = "Loc1";
	  String confRooms = "x0";
	  System.out.println("Create Basic Location functionality details...");
	  loginPage = new LoginPage(driver)
			  		.SignIn(userName, password)
			  		.SelectLocationsOption()
			  		.clickonAddButton()
			  		.fillFormAndSave(name, displayName)
			  		.SelectIssuesOption()
			  		.SelectLocationsOption()
			  		.verifyLocationsWasCreated(name, displayName, confRooms)
			  		.SignOut();	  	  		
  }  
}
