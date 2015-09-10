package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.utils.Waiters;

public class DeleteResourcesPage {	
	WebElement element;
	
	/**
	 * Method to click the Remove button
	 * @return
	 */
	public ResourcesPage Remove()
	{
		Waiters.WaitByCss("button.info");
		
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.cssSelector("button.info"));
		element.click();
		
		return new ResourcesPage();
	}
}
