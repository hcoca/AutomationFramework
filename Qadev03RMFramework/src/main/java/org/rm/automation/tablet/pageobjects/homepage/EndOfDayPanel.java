package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.tablet.locators.homepage.EndOfDayPanelLocators;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.Waiters;

public class EndOfDayPanel extends HomePage{

	@FindBy(xpath = EndOfDayPanelLocators.MainPanelLocator)
	private WebElement mainPanel;
	
	public EndOfDayPanel(WebDriver driver) {
		super(driver);
	}
	
	public MeetingsPage clickOnMainPanel(){
		Waiters.WaitByVisibilityOfWebElement(mainPanel, driver);
		mainPanel.click();
		
		return new MeetingsPage(driver);
	}

	public void waitForMainPanel() {
		Waiters.WaitByVisibilityOfWebElement(mainPanel, driver);
	}
}
