package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RMResourceAssociationPage extends RMConferenceRoomCommonPage{
	
	WebDriver driver;
	
	@FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/div/div[2]/div[1]/legend")
	public WebElement availableLabel;
	
	public RMResourceAssociationPage(WebDriver driver){
		super(driver);
	}
	
	public String getAvailablelabel(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(availableLabel));
		
		return availableLabel.getText();
	}
}
