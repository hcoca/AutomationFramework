package org.rm.automation.admin.tests.resources;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.base.TestBaseSetup;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.api.ResourcesRequests;
import org.testng.annotations.*;

public class CreateResource extends TestBaseSetup {
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	String username = settings.getProperty("username");
	String password = settings.getProperty("password");
	String name = "newResource";
	String displayName = "newResource";
	private WebDriver driver;
	private LoginPage loginPage;
	
	@BeforeClass
  	public void setUp() throws Exception {
	  driver=getDriver();
	}
	
	@Test
	public void CreateResource()
	{
		System.out.println("Enter Create Resource Test Case");
		loginPage = new LoginPage(driver)
		  		.SignIn(username, password)
		  		.SelectResourcesOption()
				.AddResource()
				.setName(name)
				.setDisplayName(displayName)
				.Save()
				.VerifyResourceWasCreated(name, displayName)
				.SignOut();
	}
	
	@AfterTest
	public void Postconditions()
	{
		System.out.println("After Test - Create Resource");
		String id = "";
		try {
		id = ResourcesRequests.getResourceId(name);
		ResourcesRequests.deleteResource(id);
	
		} catch (UnsupportedOperationException | IOException e) {
		e.printStackTrace();
		}
	}
}
