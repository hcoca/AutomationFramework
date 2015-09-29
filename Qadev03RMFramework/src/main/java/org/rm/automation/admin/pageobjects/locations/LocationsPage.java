package org.rm.automation.admin.pageobjects.locations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class LocationsPage extends HomePage{
	private Actions ac;	
	private By cellName;
	private By cellDisplayName;
	private By cellConfRoom;
	private By confirmMessage;
	@FindBy(xpath="//button[@ui-sref='admin.locations.modal.add']")
	  WebElement addBtn;
	@FindBy(xpath="//button[@ui-sref='admin.locations.remove']")
	  WebElement removeBtn;
	/*@FindBy(xpath ="//span[@class='ngLabel ng-binding' and contains(.,'Total Items:')]")
	  WebElement locationsCounter;*/
	public LocationsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		ac = new Actions(driver);
	}
	public boolean verifyLocationName(String name) {
		Waiters.WaitByXPath("//span[contains(.,'"+name+"')]", driver);
		cellName = By.xpath("//span[contains(.,'"+name+"')]");		
		WebElement element = driver.findElement(cellName);
		String locationName = element.getText();
		String expectedLocationName = name;
		return locationName.equals(expectedLocationName);
	}
	public boolean verifyLocationDisplayName(String displayName) {
		Waiters.WaitByXPath("//div[@ng-dblclick='editLocation(row.entity)'and contains(.,'"+displayName+"')]", driver);
		cellDisplayName = By.xpath("//div[@ng-dblclick='editLocation(row.entity)'and contains(.,'"+displayName+"')]");		
		WebElement element = driver.findElement(cellDisplayName);
		String locationDisplayName = element.getText();
		String expectedLocationDisplayName = displayName;
		return locationDisplayName.contains(expectedLocationDisplayName);
	}
	public boolean verifyLocationConfRooms(String confRooms) {
		Waiters.WaitByXPath("//small[contains(.,'"+confRooms+"')]", driver);
		cellConfRoom = By.xpath("//small[contains(.,'"+confRooms+"')]");
		WebElement element = driver.findElement(cellConfRoom);
		String locationConfRooms = element.getText();
		String expectedLocationConfRooms = confRooms;
		return locationConfRooms.equals(expectedLocationConfRooms);
	}
	public LocationsPage verifyChangesWereMade(String name, String displayName, String confRooms ){
		Assert.assertTrue(verifyLocationName(name), "Location Name doesn't match");
		Assert.assertTrue(verifyLocationDisplayName(displayName), "Location Display Name doesn't match");
		Assert.assertTrue(verifyLocationConfRooms(confRooms), "Location COnference Rooms doesn't match");
		return this;
	}
	public LocationsPage verifyLocationsWasEdited(String confRooms ){
		Assert.assertTrue(verifyLocationConfRooms(confRooms), "Location COnference Rooms doesn't match");
		return this;
	}
	public FormLocationPage clickonAddButton() {
		Waiters.WaitByXPath("//button[@ui-sref='admin.locations.modal.add']", driver);
		if(addBtn.isDisplayed()||addBtn.isEnabled())
		{
			addBtn.click();
		}
		return new FormLocationPage(driver);
	}
	public void clickonRemoveButton() {
		Waiters.WaitByXPath("//button[@ui-sref='admin.locations.remove']", driver);
		if(removeBtn.isDisplayed()||removeBtn.isEnabled())
		{
			removeBtn.click();
		}
	}
	public RemoveLocationsPage removeLocation(String locationToRemove){
		selectLocation(locationToRemove);
		clickonRemoveButton();
		return new RemoveLocationsPage(driver);
	}
	public FormLocationPage clickonLocation(String locationToChange) {
		if(verifyLocationDisplayName(locationToChange))
		{
			WebElement element=driver.findElement(cellDisplayName);
			if(element.isDisplayed())
			{
				ac.doubleClick(element).perform();
			}
		}
		return new FormLocationPage(driver);
	}
	public void selectLocation(String locationToRemove) {
		if(verifyLocationDisplayName(locationToRemove))
		{
			WebElement element=driver.findElement(cellDisplayName);
			if(element.isDisplayed())
			{
				(element).click();
			}
		}
	}
	public HomePage verifyLocationWasDeleted(String name) {
		Waiters.WaitByXPath("//div[@class='ng-binding ng-scope' and contains(.,'Location "+name+" sucessfully removed')]", driver);
		confirmMessage = By.xpath("//div[@class='ng-binding ng-scope' and contains(.,'Location "+name+" sucessfully removed')]");
		WebElement element =driver.findElement(confirmMessage);
		Assert.assertTrue(element.isDisplayed());
		return new HomePage(driver);
	}
	
	
}
