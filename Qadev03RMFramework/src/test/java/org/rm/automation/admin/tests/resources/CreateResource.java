package org.rm.automation.admin.tests.resources;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.base.MyWebDriver;
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
	private LoginPage loginPage;
	
	@Test
	public void testCreateResource()
	{
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
	
	@AfterMethod
	public void Postconditions()
	{
		String id = "";
		try {
		id = ResourcesRequests.getResourceId(name);
		ResourcesRequests.deleteResource(id);
	
		} catch (UnsupportedOperationException | IOException e) {
		e.printStackTrace();
		}
	}
}
