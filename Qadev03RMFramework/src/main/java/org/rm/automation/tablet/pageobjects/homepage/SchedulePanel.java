package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.tablet.locators.homepage.SchedulePanelLocators;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.Waiters;

public class SchedulePanel extends HomePage	{
    
	@FindBy(css = SchedulePanelLocators.mainPanelLocator)
	private WebElement mainPanel;
	
	public SchedulePanel(WebDriver driver) {
		super(driver);
	}
	
	public MeetingsPage clickOnMainPanel(){
		Waiters.WaitByVisibilityOfWebElement(mainPanel, driver);
		mainPanel.click();
		
		return new MeetingsPage(driver);
	}
}
