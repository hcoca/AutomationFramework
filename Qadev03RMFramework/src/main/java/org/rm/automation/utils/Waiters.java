package org.rm.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Waiters extends TestBaseSetup{
	
	/**
	 * Wait by path
	 * @param path
	 */
	public static void WaitByXPath(String path, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(path)));
	}
	
	/**
	 * Wait by css
	 * @param path
	 */
	public static void WaitByCss(String path, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector(path)));
	}
	
	/**
	 * Wait by id
	 * @param id
	 */
	public static void WaitById(String id, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.id(id)));
	}
	
	/**
	 * Wait by link text
	 * @param link
	 */
	public static void WaitByLinkText(String link, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText(link)));
	}
	
	/**
	 * Wait by tagname
	 * @param link
	 */
	public static void WaitByTagname(String tagname, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.tagName(tagname)));
	}
	
	/**
	 * Wait by class name
	 * @param link
	 */
	public static void WaitByClassname(String className, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.className(className)));
	}
	
	/**
	 * Wait by partial Link
	 * @param link
	 */
	public static void WaitByPartialLink(String partialLink, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.partialLinkText(partialLink)));
	}
	
	/**
	 * Wait by visibility of WebElement.
	 * @param webElement
	 */
	public static void WaitByVisibilityOfWebElement(WebElement webElement, WebDriver driver){
		(new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(webElement));
	}
}
