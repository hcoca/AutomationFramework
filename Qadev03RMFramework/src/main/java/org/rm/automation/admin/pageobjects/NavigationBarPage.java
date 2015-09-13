package org.rm.automation.admin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.emailServers.EmailServersPage;
import org.rm.automation.admin.pageobjects.locations.LocationsPage;
import org.rm.automation.admin.pageobjects.resources.ResourcesPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.utils.Waiters;

public class NavigationBarPage {
	/*-------------------------------------------*/
	protected WebDriver driver;
	private By locationsLink = By.linkText("Locations");
	private By issuesLink = By.linkText("Issues");
	private By EmailServer = By.linkText("Email Servers");
	private By resourcesLink = By.linkText("Resources");
	private By roomsLink = By.linkText("Conference Rooms");
	
	public NavigationBarPage(WebDriver driver) {
		this.driver=driver;
	}
	public LocationsPage SelectLocationsOption()
	{
		WebElement element=driver.findElement(locationsLink);
		if(element.isDisplayed()||element.isEnabled())
			element.click();
		return new LocationsPage(driver);
	}
	public IssuesPage  SelectIssuesOption() {
		WebElement element=driver.findElement(issuesLink);
		if(element.isDisplayed()||element.isEnabled())
			element.click();
		return new IssuesPage(driver);
	}
	
	public EmailServersPage SelectEmailServersOption()
	{
		WebElement element=driver.findElement(EmailServer);
		if(element.isDisplayed()||element.isEnabled())
			element.click();
		return new EmailServersPage(driver);
	}
	
	public ResourcesPage SelectResourcesOption()
	{
		
		Waiters.WaitByLinkText("Resources", driver);
		
		WebElement element=driver.findElement(resourcesLink);
			element.click();
		
		return new ResourcesPage(driver);
	}
	
	
	public ConferenceRoomsPage SelectRoomsOption()
	{
		Waiters.WaitByLinkText("Conference Rooms", driver);
		
		WebElement element=driver.findElement(roomsLink);
		if(element.isDisplayed()||element.isEnabled())
			element.click();
		
		return new ConferenceRoomsPage(driver);
	}
}