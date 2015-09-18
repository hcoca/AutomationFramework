package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class AddResourcesPage {
	
	private WebDriver driver;
	private Actions action;
	
	private final String namePath = "(//input[@type='text'])[3]";
	private final String displayNamePath = "(//input[@type='text'])[4]";
	private final String savePath = "button.info";
	private final String descriptionPath = "//textarea";
	private final String iconPickerPath = "convert";
	private final String tableIconsPath = "table-icons";
	
	@FindBy(xpath=namePath) WebElement nameField;
	@FindBy(xpath=displayNamePath) WebElement displayNameField;
	@FindBy(css=savePath) WebElement saveButton;
	@FindBy(xpath=descriptionPath) WebElement descriptionField;
	@FindBy(id=iconPickerPath) WebElement iconPickerButton;
	@FindBy(css=tableIconsPath) WebElement tableIcons;
	
	public AddResourcesPage(WebDriver driver) {
		this.driver=driver;
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
		
	}
	
	/**
	 * Method to set the name of a resource
	 * @param name
	 * @return
	 */
	public AddResourcesPage setName(String name)
	{
		Waiters.WaitByXPath(namePath, driver);
		
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
		Waiters.WaitById(iconPickerPath, driver);
		iconPickerButton.click();
		WebElement el;
		
		el = driver.findElement(By.xpath("//button[@value='"+icon+"']"));
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
	
	public void VerifyDescriptionResource(String expDescription)
	{
		Waiters.WaitByXPath(descriptionPath, driver);
		LogManager.info("AddResourcesPage: Verifying the description of the resource");
		String description = descriptionField.getAttribute("value");
		
		Assert.assertEquals(description, expDescription);
	}
}
