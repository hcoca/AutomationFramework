package org.rm.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserManager {

	private static BrowserManager instance = null;
	private static WebDriver driver = null;
	
	private BrowserManager(){}
	
	/**
	 * @param browser
	 * @return WebDriver, either FirefoxDriver or ChromeDriver.
	 */
	public static WebDriver getDriver(String browser){
		switch (browser) {
			case "FIREFOX":
				return initFirefoxDriver();
			case "CHROME":
				return initChromeDriver();	
			default:
				return initFirefoxDriver();
		}
	}
	
	/**
	 * @return FirefoxDriver.
	 */
	public static WebDriver initFirefoxDriver(){
		if(driver == null){
			driver = new FirefoxDriver();
		}
		return driver;
	}
	
	/**
	 * @return ChromeDriver.
	 */
	public static WebDriver initChromeDriver(){
		if(driver == null){
			System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}

}
