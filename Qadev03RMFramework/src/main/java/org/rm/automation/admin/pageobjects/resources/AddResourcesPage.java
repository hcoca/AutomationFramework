package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;

public class AddResourcesPage {
	
	private WebElement element;
	private WebDriver driver;
	
	private String namePath = "(//input[@type='text'])[3]";
	private String displayNamePath = "(//input[@type='text'])[4]";
	private String savePath = "button.info";
	
	private By nameField = By.xpath(namePath);
	private By displayNameField = By.xpath(displayNamePath);
	private By saveButton = By.cssSelector(savePath);
	
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
		Waiters.WaitByXPath(namePath, driver);
		
		element = driver.findElement(nameField);
		element.clear();
		element.sendKeys(name);
		LogManager.info("AddResourcesPage: Set the Name of a resource");
		return this;
	}
	
	/**
	 * Method to set the displayName of a resource
	 * @param displayName
	 * @return
	 */
	public AddResourcesPage setDisplayName(String displayName)
	{
		element = driver.findElement(displayNameField);
		element.sendKeys(displayName);
		LogManager.info("AddResourcesPage: Set the DisplayName of a resource");
		return this;
	}
	
	/**
	 * Method to click on the Save button
	 * @return
	 */
	public ResourcesPage Save()
	{
		element = driver.findElement(saveButton);
		element.click();		
		LogManager.info("AddResourcesPage: Click on the Save button");
		return new ResourcesPage(driver);
	}
}
