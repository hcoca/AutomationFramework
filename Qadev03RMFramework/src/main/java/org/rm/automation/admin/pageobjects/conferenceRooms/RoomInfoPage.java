package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
		codeInput.clear();
		
		codeInput.sendKeys(newCode);
		
		return this;
	}
	
	public String getCode(){
		Waiters.WaitByVisibilityOfWebElement(codeInput, driver);
		return codeInput.getAttribute("value");
	}
	
	public RoomInfoPage setDisplayName(String newDisplayName){
		Waiters.WaitByVisibilityOfWebElement(displayName, driver);
		displayName.clear();
		
		displayName.sendKeys(newDisplayName);
		
		return this;
	}
	
	public RoomInfoPage clickPowerOnBtn(){
		Waiters.WaitByVisibilityOfWebElement(powerOnBtn, driver);
		powerOnBtn.click();
		
		return this;
	}
	
	public RoomInfoPage clickPowerOffBtn(){
		Waiters.WaitByVisibilityOfWebElement(powerOffBtn, driver);
		powerOffBtn.click();
		
		return this;
	}
	
	public ConferenceRoomsPage clickSaveBtn(){
		Waiters.WaitByVisibilityOfWebElement(saveBtn, driver);
		saveBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}

	public RoomInfoPage setCapacity(String updatedCapacity) {
		Waiters.WaitByVisibilityOfWebElement(capacityInput, driver);
		capacityInput.clear();
		
		capacityInput.sendKeys(updatedCapacity);
		
		return this;
	}
	
	public String getCapacity(){
		Waiters.WaitByVisibilityOfWebElement(capacityInput, driver);
		return capacityInput.getAttribute("value");
	}
}
