package org.rm.automation.admin.pageobjects.locations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.LogManager;
import org.testng.Assert;

public class LocationsPage extends HomePage{
	private Actions ac;	
	private By cellName;
	private By cellDisplayName;
	private By cellConfRoom;
	@FindBy(xpath="//button[@ui-sref='admin.locations.modal.add']")
	  WebElement addBtn;
	@FindBy(xpath="//button[@ui-sref='admin.locations.remove']")
	  WebElement removeBtn;

	public LocationsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		ac = new Actions(driver);
	}
	public boolean verifyLocationName(String name) {
		LogManager.info("LocationsPage: Verifying the correct Name of location created");
		cellName = By.xpath("//span[contains(.,'"+name+"')]");;
		WebElement element = driver.findElement(cellName);
		String locationName = element.getText();
		String expectedLocationName = name;
		return locationName.equals(expectedLocationName);
	}
	public boolean verifyLocationDisplayName(String displayName) {
		LogManager.info("LocationsPage: Verifying the correct Display Name of location created");
		cellDisplayName = By.xpath("//div[@ng-dblclick='editLocation(row.entity)'and contains(.,'"+displayName+"')]");
		WebElement element = driver.findElement(cellDisplayName);
		String locationDisplayName = element.getText();
		String expectedLocationDisplayName = displayName;
		return locationDisplayName.contains(expectedLocationDisplayName);
	}
	public boolean verifyLocationConfRooms(String confRooms) {
		LogManager.info("LocationsPage: Verifying the correct Locations Associated number of location created");
		cellConfRoom = By.xpath("//small[contains(.,'"+confRooms+"')]");
		WebElement element = driver.findElement(cellConfRoom);
		String locationConfRooms = element.getText();
		String expectedLocationConfRooms = confRooms;
		return locationConfRooms.equals(expectedLocationConfRooms);
	}
	public LocationsPage verifyLocationsWasCreated(String name, String displayName, String confRooms ){
		LogManager.info("LocationsPage: Verifying the correct data of location created");
		Assert.assertTrue(verifyLocationName(name), "Location Name doesn't match");
		Assert.assertTrue(verifyLocationDisplayName(displayName), "Location Display Name doesn't match");
		Assert.assertTrue(verifyLocationConfRooms(confRooms), "Location COnference Rooms doesn't match");
		return this;
	}
	public FormLocationPage clickonAddButton() {
		if(addBtn.isDisplayed()||addBtn.isEnabled())
		{
			LogManager.info("LocationsPage: Click on Add button to add a new location");
			addBtn.click();
		}
		return new FormLocationPage(driver);
	}
	public FormLocationPage clickonLocation(String locationToChange) {
		if(verifyLocationDisplayName(locationToChange))
		{
			WebElement element=driver.findElement(cellDisplayName);
			if(element.isDisplayed())
			{
				LogManager.info("LocationsPage: Selecting a location to edit");
				ac.doubleClick(element).perform();
			}
		}
		return new FormLocationPage(driver);
	}
	
	
}
