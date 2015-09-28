package org.rm.automation.admin.pageobjects.resources;

import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.locators.resources.ResourcesLocators;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;

import java.util.List;

public class ResourcesPage extends HomePage{

	private Actions action;
	private WebElement element;
	
	@FindBy(xpath = ResourcesLocators.addPath) WebElement addButton;
	@FindBy(id = ResourcesLocators.removePath) WebElement removeButton;
	@FindBy(xpath = ResourcesLocators.searchPath) WebElement searchTextbox;

	public ResourcesPage(WebDriver driver) {
		super(driver);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	//***PAGE ACTIONS SECTION***//
	/**
	 * Method to select the Add tab
	 * @return
	 */
	public AddResourcesPage AddResource()
	{
		Waiters.WaitByXPath(ResourcesLocators.addPath, driver);
		
		addButton.click();
		LogManager.info("ResourcesPage: Click on Add button to create a new resource");
	    
		return new AddResourcesPage(driver);
	}
	
	/**
	 * Method to select the checkbox in the resources table
	 * @return
	 */
	public ResourcesPage SelectResource()
	{
		Waiters.WaitByCss(ResourcesLocators.checkboxPath, driver);
		
		WebElement checkbox;
		List<WebElement> list = GetListResources();
		
		checkbox = list
				.get(list.size()-1)
				.findElement(By.cssSelector(ResourcesLocators.checkboxPath));
		LogManager.info("ResourcesPage: Select a resource from the resource's table");
		checkbox.click();
		
		return this;
	}
	
	/**
	 * Method to select the Remove tab
	 * @return
	 */
	public DeleteResourcesPage RemoveResource()
	{
		removeButton.click();
		LogManager.info("ResourcesPage: Click on Remove button to remove a resource");
		
		return new DeleteResourcesPage(driver);
	}
	
	/**
	 * Method to set a search field
	 * @return
	 */
	public ResourcesPage SetSearch(String name)
	{
		searchTextbox.clear();
		searchTextbox.sendKeys(name);
		return this;
	}
	
	/**
	 * Method to update the last resource of the table
	 * @return
	 */
	public AddResourcesPage UpdateResource()
	{
		List<WebElement> list = GetListResources();
		element = list.get(list.size()-1);
		
		action
		.moveToElement(element.findElement(By.cssSelector(ResourcesLocators.resourceDoubleClickPath)))
		.doubleClick().build().perform();
		
		LogManager.info("ResourcesPage: Double click on a resource of the table");
		return new AddResourcesPage(driver);
	}
	
	//***GET SECTION***//
	/**
	 * Method to get a list of the resources
	 * @return
	 */
	public List<WebElement> GetListResources()
	{
		element = driver.findElement(By.id(ResourcesLocators.resourcesTablePath));
		List<WebElement> list = element.findElements(By.xpath(ResourcesLocators.rowsPath));
		
		return list;
	}
	
	/**
	 * Verify a resource with the obligatory fields
	 * @param expName
	 * @param expDisplayName
	 * @return
	 */
	public ResourcesPage VerifyResourceWasCreated(String expName, String expDisplayName, String expIcon)
	{
		LogManager.info("ResourcesPage: Verifying the correct data of the resource was created");
		WebElement nameElement, displayNameElement, iconElement;
		
		List<WebElement> list = GetListResources();
		element = list.get(list.size()-1);
		
		nameElement = element.findElement(By.cssSelector(ResourcesLocators.nameColumnPath));
		String name = nameElement.getText().replaceAll("\\s","");
		
		displayNameElement = element.findElement(By.cssSelector(ResourcesLocators.displayNameColumnPath));
		String displayName = displayNameElement.getText().replaceAll("\\s","");
		
		iconElement = element
				.findElement(By.cssSelector(ResourcesLocators.iconColumnPath))
				.findElement(By.xpath(ResourcesLocators.iconPath));
		String iconName = iconElement.getAttribute("class");
		
		
		Assert.assertEquals(expName, name);
		Assert.assertEquals(expDisplayName, displayName);
		Assert.assertTrue(iconName.contains(expIcon));
		return this;
	}
	
	public String getName()
	{
		LogManager.info("ResourcesPage: Getting resources's name");
		WebElement nameElement;
		
		List<WebElement> list = GetListResources();
		element = list.get(list.size()-1);
		
		nameElement = element.findElement(By.cssSelector(ResourcesLocators.nameColumnPath));
		String name = nameElement.getText().replaceAll("\\s","");
		return name;
	}
	
	public String getDisplayName()
	{
		LogManager.info("ResourcesPage: Getting resource's display name");
		WebElement displayNameElement;
		
		List<WebElement> list = GetListResources();
		element = list.get(list.size()-1);
		
		displayNameElement = element.findElement(By.cssSelector(ResourcesLocators.displayNameColumnPath));
		String displayName = displayNameElement.getText().replaceAll("\\s","");
		return displayName;
	}
	
	public String getIcon()
	{
		LogManager.info("ResourcesPage: Getting resource's icon");
		WebElement iconElement;
		
		List<WebElement> list = GetListResources();
		element = list.get(list.size()-1);
		
		iconElement = element
				.findElement(By.cssSelector(ResourcesLocators.iconColumnPath))
				.findElement(By.xpath(ResourcesLocators.iconPath));
		String iconName = iconElement.getAttribute("class");
		return iconName;
	}
	
	/**
	 * Verify a resource with all the fields
	 * @param expName
	 * @param expDisplayName
	 * @return
	 */
	public ResourcesPage VerifyResourceWasCreated(String expName, String expDisplayName, String expIcon, String description)
	{
		VerifyResourceWasCreated(expName, expDisplayName, expIcon);
		
		action
		.moveToElement(element.findElement(By.cssSelector(ResourcesLocators.resourceDoubleClickPath)))
		.doubleClick().build().perform();
		
		AddResourcesPage page = new AddResourcesPage(driver);
		page.VerifyDescriptionResource(description);

		return this;
	}
	
	/**
	 * Verify a resource was deleted
	 * @param expName
	 * @param expDisplayName
	 * @return
	 */
	public ResourcesPage VerifyResourceWasDeleted(String expected)
	{
		LogManager.info("ResourcesPage: Verifying the resource was deleted");
		
		Assert.assertTrue(isElementPresent(By.cssSelector(ResourcesLocators.nameColumnPath), expected));
		
		return this;
	}
	
	/**
	 * Method to verify a resource's name was updated
	 * @param name
	 */
	public ResourcesPage VerifyResourceElementWasUpdated(String expected, int option)
	{
		List<WebElement> list = GetListResources();
		WebElement lastRow = list.get(list.size()-1);
		WebElement column;
		String actual;

		switch(option)
		{
		case 1:
			LogManager.info("ResourcesPage: Verifying the correct Name of the resource was updated");
			column = lastRow.findElement(By.cssSelector(ResourcesLocators.nameColumnPath));
			actual = column.getText().replaceAll("\\s","");
			
			Assert.assertEquals(expected, actual);
			break;
		case 2:
			LogManager.info("ResourcesPage: Verifying the correct DisplayName of the resource was updated");
			column = lastRow.findElement(By.cssSelector(ResourcesLocators.displayNameColumnPath));
			actual = column.getText().replaceAll("\\s","");
			
			Assert.assertEquals(expected, actual);
			break;
		case 3:
			LogManager.info("ResourcesPage: Verifying the correct Description of the resource was updated");
			action.moveToElement(lastRow.findElement(By.cssSelector("div.ng-scope > span.ng-binding"))).doubleClick().build().perform();
			AddResourcesPage page = new AddResourcesPage(driver);
			page.VerifyDescriptionResource(expected);
			break;
		case 4:
			LogManager.info("ResourcesPage: Verifying the correct Icon of the resource was updated");
			column = lastRow
					.findElement(By.cssSelector(ResourcesLocators.iconColumnPath))
					.findElement(By.xpath(ResourcesLocators.iconPath));
			actual = column.getAttribute("class");
			Assert.assertTrue(actual.contains(expected));

			break;
		}
		return this;
	}
	
	/**
	 * Method to verify the search of a resource
	 */
	public ResourcesPage VerifySearch(String nameExpected)
	{
		LogManager.info("ResourcesPage: Verifying the search");

		List<WebElement> list = GetListResources();
		WebElement lastRow = list.get(0);
		WebElement column;
		String actual;
		
		column = lastRow.findElement(By.cssSelector(ResourcesLocators.nameColumnPath));
		actual = column.getText().replaceAll("\\s","");
		
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(nameExpected, actual);
		
		return this;
	}
	
	private boolean isElementPresent(By by, String expected) {
	   
	    	WebElement element;
			
			List<WebElement> list = GetListResources();
			if (list.isEmpty())
				return true;
			else 
			{
				element = list.get(list.size()-1);
		    	WebElement nameElement = element.findElement(by);
		    	String actual = nameElement.getText().toString().replaceAll("\\s","");
				System.out.println("actual: " + actual);
				System.out.println("expected: " + expected);
				if(actual.equals(expected))
					return false;
				else
					return true;
			}
	}
}

