package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.tablet.locators.homepage.NowPanelLocators;
import org.rm.automation.utils.Waiters;

public class NowPanel extends HomePage{

	@FindBy(xpath = NowPanelLocators.mainPanelLocator)
	private WebElement mainPanel;
	
	@FindBy(xpath = NowPanelLocators.titleLabelLocator)
	private WebElement titleLabel;
	
	@FindBy(xpath = NowPanelLocators.organizerLabelLocator)
	private WebElement organizerLabel;

	@FindBy(xpath = NowPanelLocators.timeRemainingLabelLocator)
	private WebElement timeRemainingLabel;
	
	public NowPanel(WebDriver driver) {
		super(driver);
	}


	public void waitForMainPanel(){
		Waiters.WaitByVisibilityOfWebElement(mainPanel, driver);
	}
	
	public String getTitleLabelText(){
		Waiters.WaitByVisibilityOfWebElement(titleLabel, driver);
		
		return titleLabel.getText();
	}
	
	public String getOrganizerLabelText() {
		Waiters.WaitByVisibilityOfWebElement(organizerLabel, driver);
		
		return organizerLabel.getText();
	}
	
	public String getTimeRemainingLabel(){
		Waiters.WaitByVisibilityOfWebElement(timeRemainingLabel, driver);
		
		return timeRemainingLabel.getText();
	}

}
