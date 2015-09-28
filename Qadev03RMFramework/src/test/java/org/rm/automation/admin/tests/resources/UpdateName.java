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

public class UpdateName extends TestBaseSetup{
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private String nameNew = StringGenerator.getString();
	private String name = StringGenerator.getString();
	private String customName = StringGenerator.getString();
	private String description = StringGenerator.getString();
	private String icon = "fa fa-gift";
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ResourcesPage resourcesPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String nameExpected = nameNew;
	private String nameActual;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("UpdateName: Executing Precondition, creating a resource");
		ResourcesRequests.postResource(name, customName, icon, description);
	}
	
	@Test
	public void testUpdateName()
	{
		LogManager.info("UpdateName: Executing Test Case");
		loginPage = new LoginPage(driver);
  		homePage = loginPage.SignIn(username, password);
		resourcesPage = homePage.SelectResourcesOption()
				.UpdateResource()
				.setName(nameNew)
				.Save();
		
		nameActual = resourcesPage.getRowName();
		messageError = String.format(messageFormat, nameExpected, nameActual);
		Assert.assertEquals(nameActual, nameExpected, messageError);
		
		resourcesPage.SignOut();
	}
	
	@AfterMethod
	public void Postconditions()
	{
		String id = "";
		id = ResourcesRequests.getResourceId(nameNew);
		ResourcesRequests.deleteResource(id);
		LogManager.info("UpdateName: Executing Postcondition, removing resource created");
	}
}
