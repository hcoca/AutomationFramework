package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RMRoomInfoPage extends RMConferenceRoomCommonPage{
	
	WebDriver driver;
	
	@FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/div/div[2]/div/form/div[2]/input")
	public WebElement displayName;
	
	@FindBy(xpath = "//div[4]/div/div/div[2]/div/div/div[1]/div[2]/button[1]")
	public WebElement powerOnBtn;
	
	@FindBy(xpath = "//div[4]/div/div/div[2]/div/div/div[1]/div[2]/button[2]")
	public WebElement powerOffBtn;
	
	@FindBy(xpath = "//div[3]/div[2]/button")
	public WebElement saveBtn;
	
	public RMRoomInfoPage(WebDriver driver){
		super(driver);
	}
	
	public RMRoomInfoPage setDisplayName(String newDisplayName){
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(displayName));
		displayName.sendKeys(newDisplayName);
		
		return this;
	}
	
	public RMRoomInfoPage clickPowerOnBtn(){
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(powerOnBtn));
		powerOnBtn.click();
		
		return this;
	}
	
	public RMRoomInfoPage clickPowerOffBtn(){
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(powerOffBtn));
		powerOffBtn.click();
		
		return this;
	}
	
	public RMConferenceRoomsPage clickSaveBtn(){
		(new WebDriverWait(super.driver, 20))
		.until(ExpectedConditions.visibilityOf(saveBtn));
		saveBtn.click();
		
		return new RMConferenceRoomsPage(driver);
	}
}
