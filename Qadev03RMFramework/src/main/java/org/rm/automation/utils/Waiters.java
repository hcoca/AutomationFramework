package org.rm.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
	
	static WebDriver driver = BrowserManager
			.getInstance()
			.getBrowser();
	
	/**
	 * Wait by path
	 * @param path
	 */
	public static void WaitByXPath(String path)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(path)));
	}
	
	/**
	 * Wait by css
	 * @param path
	 */
	public static void WaitByCss(String path)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector(path)));
	}
	
	/**
	 * Wait by id
	 * @param id
	 */
	public static void WaitById(String id)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.id(id)));
	}
	
	/**
	 * Wait by link text
	 * @param link
	 */
	public static void WaitByLinkText(String link)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText(link)));
	}
	
	/**
	 * Wait by tagname
	 * @param link
	 */
	public static void WaitByTagname(String tagname)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.tagName(tagname)));
	}
	
	/**
	 * Wait by class name
	 * @param link
	 */
	public static void WaitByClassname(String className)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.className(className)));
	}
	
	/**
	 * Wait by partial Link
	 * @param link
	 */
	public static void WaitByPartialLink(String partialLink)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.partialLinkText(partialLink)));
	}

}
