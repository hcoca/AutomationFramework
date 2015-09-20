package org.rm.automation.admin.pageobjects.tablets;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppearanceTabletPage {
	
	WebDriver driver;

	@FindBy(id="palette-select")
	WebDriver selectColorPalet;
	
	public AppearanceTabletPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
