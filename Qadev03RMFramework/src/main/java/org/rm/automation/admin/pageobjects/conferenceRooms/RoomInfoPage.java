package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoomInfoPage extends ConferenceRoomCommonPage{
	
	@FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/div/div[2]/div/form/div[2]/input")
	public WebElement displayName;
	
	@FindBy(xpath = "//div[4]/div/div/div[2]/div/div/div[1]/div[2]/button[1]")
	public WebElement powerOnBtn;
	
	@FindBy(xpath = "//div[4]/div/div/div[2]/div/div/div[1]/div[2]/button[2]")
	public WebElement powerOffBtn;
	
	@FindBy(xpath = "//div[3]/div[2]/button")
	public WebElement saveBtn;
	
	@FindBy(xpath = "//input[@ng-model='selectedRoom.code']")
	public WebElement codeInput;
	
	@FindBy(xpath = "//input[@ng-model='selectedRoom.capacity']")
	public WebElement capacityInput;
	
	public RoomInfoPage(WebDriver driver){
		super(driver);
	}
	
	public RoomInfoPage setCode(String newCode){
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(codeInput));
		codeInput.clear();
		
		codeInput.sendKeys(newCode);
		
		return this;
	}
	
	public RoomInfoPage setDisplayName(String newDisplayName){
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(displayName));
		displayName.clear();
		
		displayName.sendKeys(newDisplayName);
		
		return this;
	}
	
	public RoomInfoPage clickPowerOnBtn(){
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(powerOnBtn));
		powerOnBtn.click();
		
		return this;
	}
	
	public RoomInfoPage clickPowerOffBtn(){
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(powerOffBtn));
		powerOffBtn.click();
		
		return this;
	}
	
	public ConferenceRoomsPage clickSaveBtn(){
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(saveBtn));
		saveBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}

	public RoomInfoPage setCapacity(String updatedCapacity) {
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(capacityInput));
		capacityInput.clear();
		
		capacityInput.sendKeys(updatedCapacity);
		
		return this;
	}
}
