package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OutOfOrderPlanningPage extends ConferenceRoomCommonPage {
	
	@FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/div/div/div[2]/div[1]/div[3]/form/div[3]/div/label")
	public WebElement actionsLabel;
	
	public OutOfOrderPlanningPage(WebDriver driver){
		super(driver);
	}
	
	public String getActionsLabel(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(actionsLabel));
		
		return actionsLabel.getText();
	}
}
