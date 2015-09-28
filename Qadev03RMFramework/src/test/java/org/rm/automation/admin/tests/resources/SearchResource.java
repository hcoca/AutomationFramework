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

public class SearchResource extends TestBaseSetup{
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String username = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	String name = StringGenerator.getString();
	String customName = StringGenerator.getString();
	String description = StringGenerator.getString();
	String icon = "fa fa-gift";
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ResourcesPage resourcesPage;
	
	private String messageFormat = "Expected <%s> but found <%s>";
	private String messageError;
	private String nameExpected = name;
	private String nameActual;
	private int sizeExpected = 1;
	private int sizeActual;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("UpdateName: Executing Precondition, creating a resource");
		ResourcesRequests.postResource(name, customName, icon, description);
	}
	
	@Test
	public void testSearchResource()
	{
		LogManager.info("UpdateName: Executing Test Case");
		loginPage = new LoginPage(driver);
		homePage = loginPage.SignIn(username, password);
		resourcesPage = homePage.SelectResourcesOption()
				.SetSearch(name);
		
		sizeActual = resourcesPage.getListFoundSize();
		messageError = String.format(messageFormat, sizeExpected, sizeActual);
		Assert.assertEquals(sizeActual, sizeExpected, messageError);
		
		nameActual = resourcesPage.getSearchResult();
		messageError = String.format(messageFormat, nameExpected, nameActual);
		Assert.assertEquals(nameActual, nameExpected, messageError);
		
		resourcesPage.SignOut();
	}
	
	@AfterMethod
	public void Postconditions()
	{
		String id = "";
		id = ResourcesRequests.getResourceId(name);
		ResourcesRequests.deleteResource(id);
		LogManager.info("UpdateName: Executing Postcondition, removing resource created");
	}

}
