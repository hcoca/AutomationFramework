package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConferenceRoomCommonPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id='breadcrumb']/a[1]")
	public WebElement roomInfoBtn;
	
	@FindBy(xpath = "//*[@id='breadcrumb']/a[2]")
	public WebElement resourceAssociationBtn;
	
	@FindBy(xpath = "//*[@id='breadcrumb']/a[3]")
	public WebElement outOfOrderPlanningBtn;
	
	public ConferenceRoomCommonPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public RoomInfoPage clickRoomInfoBtn(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(roomInfoBtn));
		roomInfoBtn.click();
		
		return new RoomInfoPage(driver);
	}
	
	public ResourceAssociationPage clickResourceAssociationBtn(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(resourceAssociationBtn));
		resourceAssociationBtn.click();
		
		return new ResourceAssociationPage(driver);
	}

	public OutOfOrderPlanningPage clickOutOfOrderPlanningBtn(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(outOfOrderPlanningBtn));
		outOfOrderPlanningBtn.click();
		
		return new OutOfOrderPlanningPage(driver);
	}
}
