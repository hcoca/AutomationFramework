package org.rm.automation.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.emailServers.EmailServersPage;
//import org.rm.automation.admin.pageobjects.resources.ResourcesPage;

public class NavigationBarPage {
	/*public ResourcesPage SelectResourcesOption()
	{
		Waiters.WaitByLinkText("Resources");
		
		WebElement resources = BrowserManager.getInstance().getBrowser().findElement(By.linkText("Resources"));
		resources.click();
		
		return new ResourcesPage();
	}
	*/
	public ConferenceRoomsPage SelectRoomsOption()
	{
		Waiters.WaitByLinkText("Conference Rooms");
		
		WebElement resources = BrowserManager.getInstance().getBrowser().findElement(By.linkText("Conference Rooms"));
		resources.click();
		
		return new ConferenceRoomsPage();
	}
	
	public EmailServersPage SelectEmailServersOption()
	{
		Waiters.WaitByLinkText("Email Servers");
		
		WebElement resources = BrowserManager.getInstance().getBrowser().findElement(By.linkText("Email Servers"));
		resources.click();
		
		return new EmailServersPage();
	}
}
