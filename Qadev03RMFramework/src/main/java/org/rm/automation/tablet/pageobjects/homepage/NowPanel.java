package org.rm.automation.tablet.pageobjects.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rm.automation.utils.Waiters;

public class NowPanel extends HomePage{

	@FindBy(xpath = "//div[@ng-bind='current._title']")
	private WebElement titleLabel;
	
	@FindBy(xpath = "//div[@class='tile-now meeting']")
	private WebElement mainPanel;
	
	public NowPanel(WebDriver driver) {
		super(driver);
	}

	public String getTitleLabelText(){
		Waiters.WaitByVisibilityOfWebElement(titleLabel, driver);
		
		return titleLabel.getText();
	}
	
	public void waitForMainPanel(){
		Waiters.WaitByVisibilityOfWebElement(mainPanel, driver);
	}
}