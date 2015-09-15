package org.rm.automation.admin.pageobjects.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;

public class DeleteResourcesPage {	
	private WebElement element;
	private WebDriver driver;
	
	private String removePath = "button.info";
	private By removeButton = By.cssSelector(removePath);
	
	public DeleteResourcesPage(WebDriver driver) {
		this.driver=driver;
	}
	
	/**
	 * Method to click the Remove button
	 * @return
	 */
	public ResourcesPage Remove()
	{
		Waiters.WaitByCss(removePath, driver);
		
		element = driver.findElement(removeButton);
		element.click();
		LogManager.info("DeleteResourcesPage: Click the Remove button");
		
		return new ResourcesPage(driver);
	}
}
