package org.rm.automation.admin.pageobjects.resources;

//import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.utils.BrowserManager;

public class AddResourcesPage {
	
	/**
	 * Method to set the name of a resource
	 * @param name
	 * @return
	 */
	public AddResourcesPage setName(String name)
	{
		WaitByXPath("(//input[@type='text'])[3]");
		
		WebElement nameR = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.xpath("(//input[@type='text'])[3]"));
		nameR.clear();
		nameR.sendKeys(name);
		
		return this;
	}
	
	/**
	 * Method to set the displayName of a resource
	 * @param displayName
	 * @return
	 */
	public AddResourcesPage setDisplayName(String displayName)
	{
		WebElement displayNameR = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.xpath("(//input[@type='text'])[4]"));
		displayNameR.sendKeys(displayName);
		
		return this;
	}
	
	/**
	 * Method to click on the Save button
	 * @return
	 */
	public ResourcesPage Save()
	{
		WebElement accept = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.cssSelector("button.info"));
		accept.click();		
		
		return new ResourcesPage();
	}
	
	private void WaitByXPath(String path)
	{
		WebDriverWait wait = new WebDriverWait(BrowserManager
				.getInstance()
				.getBrowser(), 10);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(path)));
	}
}
