package org.rm.automation.admin.tests.resources;

import java.io.IOException;
import java.util.Properties;

import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.resources.DeleteResourcesPage;
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

public class DeleteResourceAnother extends TestBaseSetup{
	
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private String name = StringGenerator.getString();
	private String customName = StringGenerator.getString();
	private String description = StringGenerator.getString();
	private String icon = "fa fa-gift";
	private String nameOther = StringGenerator.getString();
	private String customNameOther = StringGenerator.getString();
	private String descriptionOther = StringGenerator.getString();
	private String iconOther = "fa fa-gift";
	
	private LoginPage loginPage;	
	private HomePage homePage;
	private ResourcesPage resourcesPage;
	private DeleteResourcesPage deleteResourcesPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String nameExpected = name;
	private boolean actual;
	private boolean expected = true;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("DeleteResourceAnother: Executing Precondition, creating a resource");
		ResourcesRequests.postResource(nameOther, customNameOther, iconOther, descriptionOther);
		ResourcesRequests.postResource(name, customName, icon, description);
	}
	
	@Test
	public void testDeleteResource()
	{
		LogManager.info("DeleteResourceAnother: Executing Test Case");
		
		loginPage = new LoginPage(driver);
  		homePage = loginPage.SignIn(username, password);
  		resourcesPage = homePage.SelectResourcesOption();
  		deleteResourcesPage = resourcesPage.SelectResource()
				.RemoveResource();
		resourcesPage = deleteResourcesPage.Remove();
		
		actual = resourcesPage.isResourceDeleted(nameExpected);
		messageError = String.format(messageFormat, expected, actual);
		Assert.assertTrue(actual, messageError);

		resourcesPage.SignOut();
	}
	
	@AfterMethod
	public void Postconditions()
	{
		String id = "";
		id = ResourcesRequests.getResourceId(nameOther);
		ResourcesRequests.deleteResource(id);
		LogManager.info("DeleteResourceAnother: Executing Postcondition, removing resource created");
	}
}
