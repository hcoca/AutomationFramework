package org.rm.automation.admin.pageobjects;

//import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.pageobjects.conferenceRooms.ConferenceRoomsPage;
import org.rm.automation.admin.pageobjects.emailServers.EmailServersPage;
import org.rm.automation.admin.pageobjects.resources.ResourcesPage;
import org.rm.automation.utils.BrowserManager;

public class NavigationBarPage {
	public ResourcesPage SelectResourcesOption()
	{
		WaitByLinkText("Resources");
		
		WebElement resources = BrowserManager.getInstance().getBrowser().findElement(By.linkText("Resources"));
		resources.click();
		
		return new ResourcesPage();
	}
	
	public ConferenceRoomsPage SelectRoomsOption()
	{
		WaitByLinkText("Conference Rooms");
		
		WebElement resources = BrowserManager.getInstance().getBrowser().findElement(By.linkText("Conference Rooms"));
		resources.click();
		
		return new ConferenceRoomsPage();
	}
	
	public EmailServersPage SelectEmailServersOption()
	{
		WaitByLinkText("Email Servers");
		
		WebElement resources = BrowserManager.getInstance().getBrowser().findElement(By.linkText("Email Servers"));
		resources.click();
		
		return new EmailServersPage();
	}
	
	private boolean isElementPresent(By by) {
	    try {
	    	BrowserManager.getInstance().getBrowser().findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
	
	private void WaitByLinkText(String link)
	{
		WebDriverWait wait = new WebDriverWait(BrowserManager
				.getInstance()
				.getBrowser(), 10);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText(link)));
	}

}
