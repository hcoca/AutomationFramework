package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.locators.conferenceRooms.RoomInfoLocators;
import org.rm.automation.utils.Waiters;

public class RoomInfoPage extends ConferenceRoomCommonPage{
	
	@FindBy(xpath = RoomInfoLocators.DisplayNameLocator)
	public WebElement displayName;
	
	@FindBy(xpath = RoomInfoLocators.PowerOnBtnLocator)
	public WebElement powerOnBtn;
	
	@FindBy(xpath = RoomInfoLocators.PowerOffBtnLocator)
	public WebElement powerOffBtn;
	
	@FindBy(xpath = RoomInfoLocators.SaveBtnLocator)
	public WebElement saveBtn;
	
	@FindBy(xpath = RoomInfoLocators.CodeInputLocator)
	public WebElement codeInput;
	
	@FindBy(xpath = RoomInfoLocators.CapacityInputLocator)
	public WebElement capacityInput;
	
	public RoomInfoPage(WebDriver driver){
		super(driver);
	}
	
	public RoomInfoPage setCode(String newCode){
		Waiters.WaitByVisibilityOfWebElement(codeInput, driver);
//		(new WebDriverWait(super.driver, 20))
//		.until(ExpectedConditions.visibilityOf(codeInput));
		codeInput.clear();
		
		codeInput.sendKeys(newCode);
		
		return this;
	}
	
	public RoomInfoPage setDisplayName(String newDisplayName){
		Waiters.WaitByVisibilityOfWebElement(displayName, driver);
//		(new WebDriverWait(super.driver, 20))
//		.until(ExpectedConditions.visibilityOf(displayName));
		displayName.clear();
		
		displayName.sendKeys(newDisplayName);
		
		return this;
	}
	
	public RoomInfoPage clickPowerOnBtn(){
		Waiters.WaitByVisibilityOfWebElement(powerOnBtn, driver);
//		(new WebDriverWait(super.driver, 20))
//		.until(ExpectedConditions.visibilityOf(powerOnBtn));
		powerOnBtn.click();
		
		return this;
	}
	
	public RoomInfoPage clickPowerOffBtn(){
		Waiters.WaitByVisibilityOfWebElement(powerOffBtn, driver);
//		(new WebDriverWait(super.driver, 20))
//		.until(ExpectedConditions.visibilityOf(powerOffBtn));
		powerOffBtn.click();
		
		return this;
	}
	
	public ConferenceRoomsPage clickSaveBtn(){
		Waiters.WaitByVisibilityOfWebElement(saveBtn, driver);
//		(new WebDriverWait(super.driver, 20))
//		.until(ExpectedConditions.visibilityOf(saveBtn));
		saveBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}

	public RoomInfoPage setCapacity(String updatedCapacity) {
		Waiters.WaitByVisibilityOfWebElement(capacityInput, driver);
//		(new WebDriverWait(super.driver, 20))
//		.until(ExpectedConditions.visibilityOf(capacityInput));
		capacityInput.clear();
		
		capacityInput.sendKeys(updatedCapacity);
		
		return this;
	}
}
