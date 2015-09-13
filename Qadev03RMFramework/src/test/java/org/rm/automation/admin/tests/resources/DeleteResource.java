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

public class DeleteResource extends TestBaseSetup{
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	private LoginPage loginPage;	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		ResourcesRequests
		.postResource();
	}
	
	@Test
	public void testDeleteResource()
	{
		System.out.println("Enter Delete Resource Test Case");
		String name = "gift";
		String displayName = "gift";
		
		loginPage = new LoginPage(driver)
		  		.SignIn(username, password)
		  		.SelectResourcesOption()
		  		.SelectResource()
				.RemoveResource()
				.Remove()
				.VerifyResourceWasDeleted(name, displayName)
				.SignOut();
	}
}
