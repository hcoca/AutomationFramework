package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.utils.BrowserManager;

public class DeleteResourcesPage {
	
	WebElement element;
	public ResourcesPage Remove()
	{
		WaitByCss("button.info");
		
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.cssSelector("button.info"));
		element.click();
		
		return new ResourcesPage();
	}
	
	/**
	 * Wait by css
	 * @param path
	 */
	private void WaitByCss(String path)
	{
		WebDriverWait wait = new WebDriverWait(BrowserManager
				.getInstance()
				.getBrowser(), 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector(path)));
	}
}
