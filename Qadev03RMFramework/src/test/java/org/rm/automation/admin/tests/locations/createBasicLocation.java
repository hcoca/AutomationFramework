package org.rm.automation.admin.tests.locations;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.rm.automation.base.MyWebDriver;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.locations.FormLocationPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.admin.pageobjects.locations.LocationsPage;

public class createBasicLocation extends TestBaseSetup{
	public static MyWebDriver myWebDriver;
	private WebDriver driver;
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private LoginPage loginPage;
	private HomePage homePage;
	private LocationsPage locationsPage;
	private IssuesPage issuesPage;
	private FormLocationPage formLocationPage;

  @BeforeClass
  public void setUp() throws Exception {
	  driver=myWebDriver.myDriver;
  }

  @Test
  public void testCreateBasicLocation() throws Exception {
	  String userName = settings.getProperty("username");
	  String password = settings.getProperty("password");
	  String name = "Location 1";
	  String displayName = "Loc1";
	  String confRooms = "x0";
	  System.out.println("Create Basic Location functionality details...");
	  loginPage = new LoginPage(driver);
	  homePage = loginPage.SignIn(userName, password);
	  locationsPage	= homePage.SelectLocationsOption();
	  formLocationPage = locationsPage.clickonAddButton();
	  homePage = formLocationPage.fillFormAndSave(name, displayName);
	  issuesPage = homePage.SelectIssuesOption();
	  locationsPage = homePage.SelectLocationsOption();
	  homePage = locationsPage.verifyLocationsWasCreated(name, displayName, confRooms);
	  homePage.SignOut();	  	  		
  }  
}
