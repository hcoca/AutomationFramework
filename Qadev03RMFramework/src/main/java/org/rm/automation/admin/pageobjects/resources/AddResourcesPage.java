package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.locators.resources.AddResourcesLocators;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;

public class AddResourcesPage {
	
	private WebDriver driver;
	
	@FindBy(xpath = AddResourcesLocators.namePath) WebElement nameField;
	@FindBy(xpath = AddResourcesLocators.displayNamePath) WebElement displayNameField;
	@FindBy(css = AddResourcesLocators.savePath) WebElement saveButton;
	@FindBy(xpath = AddResourcesLocators.descriptionPath) WebElement descriptionField;
	@FindBy(id = AddResourcesLocators.iconPickerPath) WebElement iconPickerButton;
	@FindBy(css = AddResourcesLocators.tableIconsPath) WebElement tableIcons;
	
	public AddResourcesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Method to set the name of a resource
	 * @param name
	 * @return
	 */
	public AddResourcesPage setName(String name)
	{
		Waiters.WaitByXPath(AddResourcesLocators.namePath, driver);
		
		nameField.clear();
		nameField.sendKeys(name);
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
		displayNameField.clear();
		displayNameField.sendKeys(displayName);
		LogManager.info("AddResourcesPage: Set the DisplayName of a resource");
		return this;
	}
	
	/**
	 * Method to set the description of a resource
	 * @param description
	 * @return
	 */
	public AddResourcesPage setDescription(String description)
	{
		descriptionField.clear();
		descriptionField.sendKeys(description);
		LogManager.info("AddResourcesPage: Set the Description of a resource");
		return this;
	}
	
	public AddResourcesPage setIcon(String icon)
	{
		Waiters.WaitById(AddResourcesLocators.iconPickerPath, driver);
		iconPickerButton.click();
		WebElement el;
		
		el = driver.findElement(By.xpath(AddResourcesLocators.iconPath.replace("icon", icon)));
		el.click();
		
		return this;
	}
	
	/**
	 * Method to click on the Save button
	 * @return
	 */
	public ResourcesPage Save()
	{
		saveButton.click();		
		LogManager.info("AddResourcesPage: Click on the Save button");
		
		return new ResourcesPage(driver);
	}
	
	/**
	 * Get a resource's description
	 * @return
	 */
	public String getDescription()
	{
		Waiters.WaitByXPath(AddResourcesLocators.descriptionPath, driver);
		LogManager.info("AddResourcesPage: Getting resource's description");
		String description = descriptionField.getAttribute("value");
		return description;
	}
}
