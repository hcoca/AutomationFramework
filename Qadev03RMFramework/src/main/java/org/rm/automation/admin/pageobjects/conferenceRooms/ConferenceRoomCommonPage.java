package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	
	@FindBy(xpath = ConferenceRoomCommonLocators.EmailRoomLocator)
	private WebElement emailRoom;
	
	@FindBy (xpath  = ConferenceRoomCommonLocators.NameRoomLocator)
	private WebElement nameRoom;
	
	public ConferenceRoomCommonPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getemailroom() {
		Waiters.WaitByVisibilityOfWebElement(emailRoom, driver);
		return emailRoom.getText();
	}
	public String getNameRoom() {
		Waiters.WaitByVisibilityOfWebElement(nameRoom, driver);
		return nameRoom.getText();
	}
	
	public RoomInfoPage clickRoomInfoBtn(){
		Waiters.WaitByVisibilityOfWebElement(roomInfoBtn, driver);
		roomInfoBtn.click();
		
		return new RoomInfoPage(driver);
	}
	
	public ResourceAssociationPage clickResourceAssociationBtn(){
		Waiters.WaitByVisibilityOfWebElement(resourceAssociationBtn, driver);
		resourceAssociationBtn.click();
		
		return new ResourceAssociationPage(driver);
	}

	public OutOfOrderPlanningPage clickOutOfOrderPlanningBtn(){
		Waiters.WaitByVisibilityOfWebElement(outOfOrderPlanningBtn, driver);
		outOfOrderPlanningBtn.click();
		
		return new OutOfOrderPlanningPage(driver);
	}
}
