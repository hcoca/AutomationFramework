package org.rm.automation.admin.pageobjects.emailServers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.utils.BrowserManager;

public class AddEmailServersPage {

	WebElement element;
	
	/**
	 * Method to set the hostname
	 * @param hostname
	 * @return
	 */
	public AddEmailServersPage SetHostname(String hostname)
	{
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.id("add-mailserver-hostname"));
		element.sendKeys(hostname);
		
		return this;
	}
	
	/**
	 * Method to set the username of the exchange server
	 * @param username
	 * @return
	 */
	public AddEmailServersPage SetUsername(String username)
	{
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.id("add-mailserver-username"));
		element.sendKeys(username);
		
		return this;
	}
	
	/**
	 * Method to set the password of the exchange server
	 * @param password
	 * @return
	 */
	public AddEmailServersPage SetPassword(String password)
	{
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.id("add-mailserver-password"));
		element.sendKeys(password);
		
		return this;
	}
	
	/**
	 * Method to click the save button
	 * @return
	 */
	public EmailServersPage Save()
	{
		WaitByCss("div.modal-footer.ng-scope > button.btn.btn-primary");
		
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.cssSelector("div.modal-footer.ng-scope > button.btn.btn-primary"));
		element.click();
		
		return new EmailServersPage();
	}
		
	/**
	 * Wait by id
	 * @param id
	 */
	private void WaitById(String id)
	{
		WebDriverWait wait = new WebDriverWait(BrowserManager
				.getInstance()
				.getBrowser(), 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.id(id)));
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
