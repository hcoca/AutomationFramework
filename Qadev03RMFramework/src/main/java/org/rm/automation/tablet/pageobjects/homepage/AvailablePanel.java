package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.tablet.locators.homepage.AvailablePanelLocators;
import org.rm.automation.tablet.pageobjects.meetings.MeetingsPage;
import org.rm.automation.utils.Waiters;

public class AvailablePanel extends HomePage{

	@FindBy(xpath = AvailablePanelLocators.MainBusyPanelLocator)
	private WebElement mainBusyPanel;
	
	@FindBy(xpath = AvailablePanelLocators.MainFreePanelLocator)
	private WebElement mainFreePanel;
	
	@FindBy(xpath = AvailablePanelLocators.StartAvailableTimelabelLocator)
	private WebElement startAvailableTimelabel; 
	
	@FindBy(xpath = AvailablePanelLocators.EndAvailableTimelabelLocator)
	private WebElement endAvailableTimelabel;
	
	@FindBy(xpath = AvailablePanelLocators.AvailableTimeLeftLocator)
	private WebElement availableTimeLeft;
	
	public AvailablePanel(WebDriver driver) {
		super(driver);
	}
	
	public MeetingsPage clickOnMainFreePanel(){
		Waiters.WaitByVisibilityOfWebElement(mainFreePanel, driver);
		mainFreePanel.click();
		
		return new MeetingsPage(driver);
	}
	
	public void waitForMainBusyPanel(){
		Waiters.WaitByVisibilityOfWebElement(mainBusyPanel, driver);
	}
	
	public void waitForMainFreePanel(){
		Waiters.WaitByVisibilityOfWebElement(mainFreePanel, driver);
	}
	
	public String getStartAvailableTimeLabelText(){
		Waiters.WaitByVisibilityOfWebElement(startAvailableTimelabel, driver);
		
		return startAvailableTimelabel.getText();
	}
	
	public String getEndAvailableTimeLabelText(){
		Waiters.WaitByVisibilityOfWebElement(endAvailableTimelabel, driver);
		
		return endAvailableTimelabel.getText();
	}
	
	public String getAvailableTimeLeftText(){
		Waiters.WaitByVisibilityOfWebElement(availableTimeLeft, driver);
		
		return availableTimeLeft.getText();
	}
}
