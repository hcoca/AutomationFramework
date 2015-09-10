package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.utils.BrowserManager;
import org.rm.automation.utils.Waiters;

public class AddResourcesPage {
	
	WebElement element;
	/**
	 * Method to set the name of a resource
	 * @param name
	 * @return
	 */
	public AddResourcesPage setName(String name)
	{
		Waiters.WaitByXPath("(//input[@type='text'])[3]");
		
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.xpath("(//input[@type='text'])[3]"));
		element.clear();
		element.sendKeys(name);
		
		return this;
	}
	
	/**
	 * Method to set the displayName of a resource
	 * @param displayName
	 * @return
	 */
	public AddResourcesPage setDisplayName(String displayName)
	{
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.xpath("(//input[@type='text'])[4]"));
		element.sendKeys(displayName);
		
		return this;
	}
	
	/**
	 * Method to click on the Save button
	 * @return
	 */
	public ResourcesPage Save()
	{
		
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.cssSelector("button.info"));
		element.click();		
		
		return new ResourcesPage();
	}
}
