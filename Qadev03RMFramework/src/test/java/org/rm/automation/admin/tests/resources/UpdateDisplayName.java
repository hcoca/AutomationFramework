package org.rm.automation.admin.tests.resources;

import java.io.IOException;
import java.util.Properties;

import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.resources.ResourcesPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.StringGenerator;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ResourcesRequests;
import org.testng.Assert;
import org.testng.annotations.*;

public class UpdateDisplayName extends TestBaseSetup{
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private String displayNameNew = StringGenerator.getString();
	private String name = StringGenerator.getString();
	private String customName = StringGenerator.getString();
	private String description = StringGenerator.getString();
	private String icon = "fa fa-gift";
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ResourcesPage resourcesPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String displayNameExpected = displayNameNew;
	private String displayNameActual;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("UpdateDisplayName: Executing Precondition, creating a resource");
		ResourcesRequests.postResource(name, customName, icon, description);
	}
	
	@Test
	public void testUpdateDisplayName()
	{
		LogManager.info("UpdateDisplayName: Executing Test Case");
		loginPage = new LoginPage(driver);
  		homePage = loginPage.SignIn(username, password);
		resourcesPage = homePage.SelectResourcesOption()
				.UpdateResource()
				.setDisplayName(displayNameNew)
				.Save();
		
		displayNameActual = resourcesPage.getDisplayName();
		messageError = String.format(messageFormat, displayNameExpected, displayNameActual);
		Assert.assertEquals(displayNameActual, displayNameExpected, messageError);
		
		resourcesPage.SignOut();
	}
	
	@AfterMethod
	public void Postconditions()
	{
		String id = "";
		id = ResourcesRequests.getResourceId(name);
		ResourcesRequests.deleteResource(id);
		LogManager.info("UpdateDisplayName: Executing Postcondition, removing resource created");
	}
}
