package org.rm.automation.admin.pageobjects.resources;

import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.Waiters;

import java.util.List;

public class ResourcesPage extends HomePage{

	private Actions action;
	private WebElement element;
	
	public ResourcesPage(WebDriver driver) {
		super(driver);
		action = new Actions(driver);
	}
	
	/**
	 * Method to select the Add tab
	 * @return
	 */
	public AddResourcesPage AddResource()
	{
		Waiters.WaitByXPath("//div/div/button", driver);
		
		WebElement element = driver.findElement(By.xpath("//div/div/button"));
		element.click();
	    
		return new AddResourcesPage(driver);
	}
	
	/**
	 * Method to select the checkbox in the resources table
	 * @return
	 */
	public ResourcesPage SelectResource()
	{
		Waiters.WaitByCss("input.ngSelectionCheckbox", driver);
		
		WebElement checkbox;
		List<WebElement> list = GetListResources();
		
		checkbox = list.get(list.size()-1).findElement(By.cssSelector("input.ngSelectionCheckbox"));
		checkbox.click();
		
		return this;
	}
	
	/**
	 * Method to select the Remove tab
	 * @return
	 */
	public DeleteResourcesPage RemoveResource()
	{
		element = driver
				.findElement(By.id("btnRemove"));
		element.click();
		
		return new DeleteResourcesPage(driver);
	}
	
	/**
	 * Method to update the last resource of the table
	 * @return
	 */
	public AddResourcesPage UpdateResource()
	{
		List<WebElement> list = GetListResources();
		element = list.get(list.size()-1);
		
		action.moveToElement(element.findElement(By.cssSelector("div.ng-scope > span.ng-binding"))).doubleClick().build().perform();
		
		return new AddResourcesPage(driver);
	}
	
	/**
	 * Method to get a list of the resources
	 * @return
	 */
	public List<WebElement> GetListResources()
	{
		WebElement element2;
		WebElement element3;
		
		element = driver.findElement(By.id("resourcesGrid"));		
		
		element2 = element.findElement(By.xpath("div[2]"));
		element3 = element2.findElement(By.tagName("div"));
		
		List<WebElement> listEven = element3.findElements(By.cssSelector("div.ng-scope.ngRow.even"));
		List<WebElement> listOdd = element3.findElements(By.cssSelector("div.ng-scope.ngRow.odd"));
		
		if (listEven.size() > listOdd.size()) {
			return listEven;
		}
		else if(listEven.size() == listOdd.size()){
			return listOdd;
		}
		return null;
	}

	
	/**
	 * Verify a resource was created
	 * @param expName
	 * @param expDisplayName
	 * @return
	 */
	public ResourcesPage VerifyResourceWasCreated(String expName, String expDisplayName)
	{
		WebElement nameElement;
		WebElement displayNameElement;

		SelectRoomsOption();
		SelectResourcesOption();
		
		List<WebElement> list = GetListResources();
		
		nameElement = list.get(list.size()-1).findElement(By.cssSelector("div.ngCell.centeredColumn.col2.colt2"));
		String name = nameElement.getText().replaceAll("\\s","");
		
		displayNameElement = list.get(list.size()-1).findElement(By.cssSelector("div.ngCell.centeredColumn.col3.colt3"));
		String displayName = displayNameElement.getText().replaceAll("\\s","");
		
		Assert.assertEquals(expName, name);
		Assert.assertEquals(expDisplayName, displayName);
		System.out.println("Verifycation done");
		return this;
	}
	
	/**
	 * Verify a resource was deleted
	 * @param expName
	 * @param expDisplayName
	 * @return
	 */
	public ResourcesPage VerifyResourceWasDeleted(String expName, String expDisplayName)
	{
		WebElement nameElement;
		WebElement displayNameElement;
		
		SelectRoomsOption();
		SelectResourcesOption();
		
		List<WebElement> list = GetListResources();
		
		nameElement = list.get(list.size()-1).findElement(By.cssSelector("div.ngCell.centeredColumn.col2.colt2"));
		String name = nameElement.getText().replaceAll("\\s","");
		
		displayNameElement = list.get(list.size()-1).findElement(By.cssSelector("div.ngCell.centeredColumn.col3.colt3"));
		String displayName = displayNameElement.getText().replaceAll("\\s","");
		
		Assert.assertNotEquals(expName, name);
		Assert.assertNotEquals(expDisplayName, displayName);
		
		return this;
	}
	
	/**
	 * Method to verify a resource's name was updated
	 * @param name
	 */
	public ResourcesPage VerifyResourceNameWasUpdated(String expName)
	{
		WebElement nameElement;

		SelectRoomsOption();
		SelectResourcesOption();
		
		List<WebElement> list = GetListResources();
		
		nameElement = list.get(list.size()-1).findElement(By.cssSelector("div.ngCell.centeredColumn.col2.colt2"));
		String name = nameElement.getText().replaceAll("\\s","");
		
		Assert.assertEquals(expName, name);
		
		return this;
	}
}
