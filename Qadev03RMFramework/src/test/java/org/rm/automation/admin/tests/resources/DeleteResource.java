package org.rm.automation.admin.tests.resources;

import java.io.IOException;
import java.util.Properties;

import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.admin.pageobjects.resources.DeleteResourcesPage;
import org.rm.automation.admin.pageobjects.resources.ResourcesPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.StringGenerator;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ResourcesRequests;
import org.testng.annotations.*;

public class DeleteResource extends TestBaseSetup{
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
	private DeleteResourcesPage deleteResourcesPage;
	private IssuesPage issuesPage;
	
	@BeforeMethod
	public void Preconditions() throws UnsupportedOperationException, IOException
	{
		LogManager.info("UpdateName: Executing Precondition, creating a resource");
		ResourcesRequests.postResource(name, customName, icon, description);
	}
	
	@Test
	public void testDeleteResource()
	{
		LogManager.info("DeleteResource: Executing Test Case");
		
		loginPage = new LoginPage(driver);
  		homePage = loginPage.SignIn(username, password);
  		resourcesPage = homePage.SelectResourcesOption();
  		deleteResourcesPage = resourcesPage.SelectResource()
				.RemoveResource();
		resourcesPage = deleteResourcesPage.Remove();
		issuesPage = resourcesPage.SelectIssuesOption();
		resourcesPage = issuesPage.SelectResourcesOption()
				.VerifyResourceWasDeleted(name);
		resourcesPage.SignOut();
	}
}
