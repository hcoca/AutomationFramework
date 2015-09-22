package org.rm.automation.admin.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.emailServers.EmailServersPage;
import org.rm.automation.admin.pageobjects.impersonation.ImpersonationPage;
import org.rm.automation.admin.pageobjects.locations.LocationsPage;
import org.rm.automation.admin.pageobjects.resources.ResourcesPage;
import org.rm.automation.admin.pageobjects.tablets.TabletsAdminPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class NavigationBarPage {

	protected WebDriver driver;
	@FindBy(linkText="Locations")
	  WebElement locationsLink;
	@FindBy(linkText="Issues")
	  WebElement issuesLink;
	@FindBy(linkText="Email Servers")
	  WebElement emailServerLink;
	@FindBy(linkText="Resources")
	  WebElement resourcesLink;
	@FindBy(linkText="Conference Rooms")
	  WebElement roomsLink;
	@FindBy(linkText="Impersonation")
	  WebElement impersonationLink;
	@FindBy(linkText="Tablets")
	  WebElement tabletLink;
	
	public NavigationBarPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public LocationsPage SelectLocationsOption()
	{
		Waiters.WaitByLinkText("Locations", driver);
		if(locationsLink.isDisplayed()||locationsLink.isEnabled())
		{
			LogManager.info("NavigationBarPage: Selecting Locations option");
			locationsLink.click();
		}
		return new LocationsPage(driver);
	}
	public IssuesPage  SelectIssuesOption() {
		Waiters.WaitByLinkText("Issues", driver);
		if(issuesLink.isDisplayed()||issuesLink.isEnabled())
		{
			LogManager.info("NavigationBarPage: Selecting Issues option");
			issuesLink.click();
		}
		return new IssuesPage(driver);
	}
	
	public EmailServersPage SelectEmailServersOption()
	{
		Waiters.WaitByLinkText("Email Servers", driver);
		if(emailServerLink.isDisplayed()||emailServerLink.isEnabled())
		{
			LogManager.info("NavigationBarPage: Selecting Email Servers option");
			emailServerLink.click();
		}
		return new EmailServersPage(driver);
	}
	
	public ResourcesPage SelectResourcesOption()
	{
		
		Waiters.WaitByLinkText("Resources", driver);		
		if(resourcesLink.isDisplayed()||resourcesLink.isEnabled())
		{
			LogManager.info("NavigationBarPage: Selecting Resources option");
			resourcesLink.click();
		}		
		return new ResourcesPage(driver);
	}
	
	public ConferenceRoomsPage SelectRoomsOption()
	{
		Waiters.WaitByVisibilityOfWebElement(roomsLink, driver);
		if(roomsLink.isDisplayed()||roomsLink.isEnabled())
		{
			LogManager.info("NavigationBarPage: Selecting Conference Rooms option");
			roomsLink.click();
		}		
		return new ConferenceRoomsPage(driver);
	}
	
	public ImpersonationPage SelectImpersonationOption(){
		Waiters.WaitByVisibilityOfWebElement(impersonationLink, driver);
		if(impersonationLink.isDisplayed()||impersonationLink.isEnabled())
		{
			LogManager.info("NavigationBarPage: Selecting Impersonation option");
			impersonationLink.click();
		}		
		return new ImpersonationPage(driver);
	}
	public TabletsAdminPage SelectTabletsOptions() {
		Waiters.WaitByVisibilityOfWebElement(tabletLink, driver);
		if(tabletLink.isDisplayed()||tabletLink.isEnabled())
		{
			LogManager.info("NavigationBarPage: Selecting Tablets option");
			tabletLink.click();
		}		
		return new TabletsAdminPage(driver);
	}
	
	public HomePage mainMenuIsDisplayed(){
		Waiters.WaitByVisibilityOfWebElement(impersonationLink, driver);
		Waiters.WaitByVisibilityOfWebElement(emailServerLink, driver);
		Waiters.WaitByVisibilityOfWebElement(locationsLink, driver);
		Waiters.WaitByVisibilityOfWebElement(resourcesLink, driver);
		Waiters.WaitByVisibilityOfWebElement(roomsLink, driver);
		Waiters.WaitByVisibilityOfWebElement(issuesLink, driver);
		Assert.assertEquals(true, issuesLink.isDisplayed());
		Assert.assertEquals(true, issuesLink.isEnabled());
		Assert.assertEquals(true, impersonationLink.isDisplayed());
		Assert.assertEquals(true, impersonationLink.isEnabled());
		Assert.assertEquals(true, emailServerLink.isDisplayed());
		Assert.assertEquals(true, emailServerLink.isEnabled());
		Assert.assertEquals(true, locationsLink.isDisplayed());
		Assert.assertEquals(true, locationsLink.isEnabled());
		Assert.assertEquals(true, resourcesLink.isDisplayed());
		Assert.assertEquals(true, resourcesLink.isEnabled());
		Assert.assertEquals(true, roomsLink.isDisplayed());
		Assert.assertEquals(true, roomsLink.isEnabled());
		return new HomePage(driver);		
	}
	
	
	/*
	 * TESTING METHOD. DO NOT USE FOR TEST IMPLEMENTATION.
	 */
	public ConferenceRoomsPage click571()
	{
		(new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(roomsLink));
		roomsLink.click();				
		return new ConferenceRoomsPage(driver);
	} 
}