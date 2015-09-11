package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.utils.Waiters;

public class AddResourcesPage {
	
	private WebElement element;
	private WebDriver driver;
	
	public AddResourcesPage(WebDriver driver) {
		this.driver=driver;
	}
	
	/**
	 * Method to set the name of a resource
	 * @param name
	 * @return
	 */
	public AddResourcesPage setName(String name)
	{
		Waiters.WaitByXPath("(//input[@type='text'])[3]", driver);
		
		element = driver
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
		element = driver
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
		
		element = driver.findElement(By.cssSelector("button.info"));
		element.click();		
		
		return new ResourcesPage(driver);
	}
}
