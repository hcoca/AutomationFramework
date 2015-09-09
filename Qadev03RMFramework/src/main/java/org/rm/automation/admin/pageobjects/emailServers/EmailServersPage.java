package org.rm.automation.admin.pageobjects.emailServers;

//import static org.junit.Assert.*;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.utils.BrowserManager;
import org.testng.AssertJUnit;

public class EmailServersPage {
	
	WebElement element;
	
	public EmailServersPage()
	{
		BrowserManager
		.getInstance()
		.getBrowser()
		.manage()
		.timeouts()
		.pageLoadTimeout(100, TimeUnit.SECONDS);
	}

	public AddEmailServersPage AddEmailServer()
	{
		WaitByXPath("//div[2]/button");
		
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.xpath("//div[2]/button"));
		
		element.click();
		
		return new AddEmailServersPage();
	}
	
	public RemoveEmailServersPage RemoveEmailServer()
	{
		WaitByXPath("//button[2]");
		
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.xpath("//button[2]"));
		
		element.click();
		
		return new RemoveEmailServersPage();
	}
	
	/**
	 * Method to verify the email server was added
	 * @param hostname
	 */
	public EmailServersPage VerifyEmailServerWasAdded(String hostnameExpected)
	{
		WaitByCss("h4.ng-binding");
		
		element = BrowserManager
				.getInstance()
				.getBrowser()
				.findElement(By.cssSelector("h4.ng-binding"));
		String hostnameActual = element.getText();
		
		System.out.println(hostnameExpected);
		System.out.println(hostnameActual);
		
		Assert.assertTrue(hostnameActual.contains(hostnameExpected));
		
		return this;
	}
	
	/**
	 * Method to verify the email server was removed
	 */
	public EmailServersPage VerifyEmailServerWasRemoved()
	{
		
		Assert.assertFalse(isElementPresent(By.cssSelector("h4.ng-binding")));
		
		return this;
	}
	
	/**
	 * Wait by path
	 * @param path
	 */
	private void WaitByXPath(String path)
	{
		WebDriverWait wait = new WebDriverWait(BrowserManager
				.getInstance()
				.getBrowser(), 10);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(path)));
	}
	
	/**
	 * Wait by css
	 * @param path
	 */
	private void WaitByCss(String path)
	{
		WebDriverWait wait = new WebDriverWait(BrowserManager
				.getInstance()
				.getBrowser(), 10);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector(path)));
	}
	
	private boolean isElementPresent(By by) {
	    try {
	    	BrowserManager
			.getInstance()
			.getBrowser().findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
}
