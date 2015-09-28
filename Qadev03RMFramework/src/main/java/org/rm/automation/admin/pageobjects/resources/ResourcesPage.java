package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
	 * Get a resources's  name
	 * @return
	 */
	public String getName()
	{
		LogManager.info("ResourcesPage: Getting resources's name");
		WebElement nameElement;
		
		List<WebElement> list = GetListResources();
		if(list.isEmpty())
		{
			throw new NoSuchElementException("No resources were found");
		}
		else
		{
			element = list.get(list.size()-1);
			
			nameElement = element.findElement(By.cssSelector(ResourcesLocators.nameColumnPath));
			String name = nameElement.getText().replaceAll("\\s","");
			return name;
		}
		
	}
	
	/**
	 * Get a resource's display name
	 * @return
	 */
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
	
	/**
	 * Get a resource's icon
	 * @return
	 */
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
	 * Get a resource's description
	 * @return
	 */
	public String getDescription()
	{
		LogManager.info("ResourcesPage: Getting resource's description");

		List<WebElement> list = GetListResources();
		element = list.get(list.size()-1);
		
		action
		.moveToElement(element.findElement(By.cssSelector(ResourcesLocators.resourceDoubleClickPath)))
		.doubleClick().build().perform();
		
		AddResourcesPage page = new AddResourcesPage(driver);
		String description = page.getDescription();
		return description;
	}
	
	/**
	 * Get whether or not a resource was deleted
	 * @param expected
	 * @return
	 */
	public boolean isResourceDeleted(String expected)
	{
		return isElementPresent(By.cssSelector(ResourcesLocators.nameColumnPath), expected);
	}
	
	/**
	 * Get the list size of the found resources
	 * @return
	 */
	public int getListFoundSize()
	{
		LogManager.info("ResourcesPage: Getting list's size of found elements");

		List<WebElement> list = GetListResources();
		
		return list.size();
	}
	
	public String getSearchResult()
	{
		LogManager.info("ResourcesPage: Getting search result");

		List<WebElement> list = GetListResources();
		WebElement lastRow = list.get(0);
		WebElement column;
		String actual;
		
		column = lastRow.findElement(By.cssSelector(ResourcesLocators.nameColumnPath));
		actual = column.getText().replaceAll("\\s","");
		
		return actual;
	}
	
	private boolean isElementPresent(By by, String expected) {
	   try{
		   element = driver.findElement(By.xpath(ResourcesLocators.rowsPath));
		   
	    	WebElement element;
			
			List<WebElement> list = GetListResources();
			if (list.isEmpty())
				return true;
			else 
			{
				element = list.get(list.size()-1);
		    	WebElement nameElement = element.findElement(by);
		    	String actual = nameElement.getText().toString().replaceAll("\\s","");
				if(actual.equals(expected))
					return false;
				else
					return true;
			}
	   }
	   catch(NoSuchElementException e){
		   throw e;
	   }
	}
}

