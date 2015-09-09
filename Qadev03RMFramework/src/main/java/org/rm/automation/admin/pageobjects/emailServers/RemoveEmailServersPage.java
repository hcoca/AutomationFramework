package org.rm.automation.admin.pageobjects.emailServers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.utils.BrowserManager;

public class RemoveEmailServersPage {
	
	WebElement element;
	
	public EmailServersPage Remove()
	{
		WaitByCss("div.pull-right > button.btn-clear.info");
		
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.cssSelector("div.pull-right > button.btn-clear.info"));
		element.click();
		
		return new EmailServersPage();
	}
	
	/**
	 * Wait by css
	 * @param path
	 */
	private void WaitByCss(String path)
	{
		WebDriverWait wait = new WebDriverWait(BrowserManager
				.getInstance()
				.getBrowser(), 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector(path)));
	}
}
