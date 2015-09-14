package org.rm.automation.admin.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.emailServers.EmailServersPage;
import org.rm.automation.admin.pageobjects.locations.LocationsPage;
import org.rm.automation.admin.pageobjects.resources.ResourcesPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;

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
			LogManager.info("NavigationBarPage: Selecting Resources option");
			roomsLink.click();
		}		
		return new ConferenceRoomsPage(driver);
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