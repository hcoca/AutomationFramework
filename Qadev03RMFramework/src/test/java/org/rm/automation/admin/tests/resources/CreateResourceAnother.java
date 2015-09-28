package org.rm.automation.admin.tests.resources;

import java.util.Properties;

import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.resources.AddResourcesPage;
import org.rm.automation.admin.pageobjects.resources.ResourcesPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.StringGenerator;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ResourcesRequests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CreateResourceAnother extends TestBaseSetup{
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private String name = StringGenerator.getString();
	private String displayName = StringGenerator.getString();
	private String iconName = "fa-gears";
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ResourcesPage resourcesPage;
	private AddResourcesPage addResourcesPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String nameExpected = name;
	private String nameActual;
	private String displayNameExpected = displayName;
	private String displayNameActual;
	private String iconNameExpected = iconName;
	private String iconNameActual;
	
	@Test
	public void testCreateResource()
	{
		LogManager.info("CreateResource: Executing Test Case");
		loginPage = new LoginPage(driver);
  		homePage = loginPage.SignIn(username, password);
  		resourcesPage = homePage.SelectResourcesOption();		  		
		addResourcesPage = resourcesPage.AddResource()
				.setName(name)
				.setDisplayName(displayName)
				.setIcon(iconName);
		resourcesPage =	addResourcesPage.Save();		
		
		nameActual = resourcesPage.getName();
		messageError = String.format(messageFormat, nameExpected, nameActual);
		Assert.assertEquals(nameActual, nameExpected, messageError);
		
		displayNameActual = resourcesPage.getDisplayName();
		messageError = String.format(messageFormat, displayNameExpected, displayNameActual);
		Assert.assertEquals(displayNameActual, displayNameExpected, messageError);
		
		iconNameActual = resourcesPage.getIcon();
		messageError = String.format(messageFormat, iconNameExpected, iconNameActual);
		Assert.assertTrue(iconNameActual.contains(iconNameExpected));
		
		resourcesPage.SignOut();
	}
	
	@AfterMethod
	public void Postconditions()
	{
		String id = "";
		id = ResourcesRequests.getResourceId(name);
		ResourcesRequests.deleteResource(id);
		LogManager.info("CreateResource: Executing Postcondition, removing resource created");
	}
}
