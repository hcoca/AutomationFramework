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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateDescription extends TestBaseSetup{
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private String descriptionNew = StringGenerator.getString();
	String name = StringGenerator.getString();
	String customName = StringGenerator.getString();
	String description = StringGenerator.getString();
	String icon = "fa fa-gift";
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ResourcesPage resourcesPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String descriptionExpected = descriptionNew;
	private String descriptionActual;
	
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("UpdateDescription: Executing Precondition, creating a resource");
		ResourcesRequests.postResource(name, customName, icon, description);
	}
	
	@Test
	public void testUpdateDescription()
	{
		LogManager.info("UpdateDescription: Executing Test Case");
		loginPage = new LoginPage(driver);
  		homePage = loginPage.SignIn(username, password);
		resourcesPage = homePage.SelectResourcesOption()
				.UpdateResource()
				.setDescription(descriptionNew)
				.Save();
		
		descriptionActual = resourcesPage.getDescription();
		messageError = String.format(messageFormat, descriptionExpected, descriptionActual);
		Assert.assertEquals(descriptionActual, descriptionExpected, messageError);

		resourcesPage.SignOut();
	}
	
	@AfterMethod
	public void Postconditions()
	{
		String id = "";
		id = ResourcesRequests.getResourceId(name);
		ResourcesRequests.deleteResource(id);
		LogManager.info("UpdateDescription: Executing Postcondition, removing resource created");
	}
}
