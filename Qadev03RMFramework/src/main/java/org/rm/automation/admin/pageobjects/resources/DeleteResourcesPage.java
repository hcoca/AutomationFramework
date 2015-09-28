package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;

public class DeleteResourcesPage {	
	private WebElement element;
	private WebDriver driver;
	
	private final String removePath = "button.info";
	@FindBy(css=removePath) WebElement removeButton;
	
	public DeleteResourcesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Method to click the Remove button
	 * @return
	 */
	public ResourcesPage Remove()
	{
		Waiters.WaitByCss(removePath, driver);
		
		removeButton.click();
		LogManager.info("DeleteResourcesPage: Click the Remove button");
		
		return new ResourcesPage(driver);
	}
}
