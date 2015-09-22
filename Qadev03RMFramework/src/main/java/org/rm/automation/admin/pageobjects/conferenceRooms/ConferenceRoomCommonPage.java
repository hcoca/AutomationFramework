package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.locators.conferenceRooms.ConferenceRoomCommonLocators;
import org.rm.automation.utils.Waiters;

public class ConferenceRoomCommonPage {
	
	WebDriver driver;
	
	@FindBy(xpath = ConferenceRoomCommonLocators.RoomInfoBtnLocator)
	public WebElement roomInfoBtn;
	
	@FindBy(xpath = ConferenceRoomCommonLocators.ResourceAssociationBtnLocator)
	public WebElement resourceAssociationBtn;
	
	@FindBy(xpath = ConferenceRoomCommonLocators.OutOfOrderPlanningBtnLocator)
	public WebElement outOfOrderPlanningBtn;
	
	// all pages have this properties
	@FindBy(xpath = "//h5[@class='ng-binding']")
	private WebElement emailroom;
	
	@FindBy (xpath  = "//h2[@class='ng-binding']")
	private WebElement nameroom;
	
	//----
	
	public ConferenceRoomCommonPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getemailroom() {
		Waiters.WaitByVisibilityOfWebElement(emailroom, driver);
		return emailroom.getText();
	}
	public String getNameRoom() {
		Waiters.WaitByVisibilityOfWebElement(nameroom, driver);
		return nameroom.getText();
	}
	
	public RoomInfoPage clickRoomInfoBtn(){
		Waiters.WaitByVisibilityOfWebElement(roomInfoBtn, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(roomInfoBtn));
		roomInfoBtn.click();
		
		return new RoomInfoPage(driver);
	}
	
	public ResourceAssociationPage clickResourceAssociationBtn(){
		Waiters.WaitByVisibilityOfWebElement(resourceAssociationBtn, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(resourceAssociationBtn));
		resourceAssociationBtn.click();
		
		return new ResourceAssociationPage(driver);
	}

	public OutOfOrderPlanningPage clickOutOfOrderPlanningBtn(){
		Waiters.WaitByVisibilityOfWebElement(outOfOrderPlanningBtn, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(outOfOrderPlanningBtn));
		outOfOrderPlanningBtn.click();
		
		return new OutOfOrderPlanningPage(driver);
	}
}
