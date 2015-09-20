package org.rm.automation.admin.pageobjects.tablets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.HomePage;

public class TabletsPage extends HomePage {
	@FindBy(xpath = "//button[@ui-sref='admin.tablet.modal.appearance']")
	WebElement button;
	
	
	public TabletsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(super.driver, this);
	}
	public AppearanceTabletPage appearenceTabletButton() {
		button.click();
		return new AppearanceTabletPage(driver);
	}	
	

}
