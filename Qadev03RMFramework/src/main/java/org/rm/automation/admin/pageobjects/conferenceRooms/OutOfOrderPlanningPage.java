package org.rm.automation.admin.pageobjects.conferenceRooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.locators.conferenceRooms.OutOfOrderPlanningLocators;
import org.rm.automation.utils.Waiters;

public class OutOfOrderPlanningPage extends ConferenceRoomCommonPage {
	
	@FindBy(xpath = OutOfOrderPlanningLocators.ActionsLabelLocator)
	public WebElement actionsLabel;
	
	public OutOfOrderPlanningPage(WebDriver driver){
		super(driver);
	}
	
	public String getActionsLabel(){
		Waiters.WaitByVisibilityOfWebElement(actionsLabel, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(actionsLabel));
		
		return actionsLabel.getText();
	}
}
