package org.rm.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserManager {

	private static BrowserManager instance = null;
	private static WebDriver driver = null;
	
	private BrowserManager(){}
	
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
	
	public static WebDriver initFirefoxDriver(){
		if(driver == null || driver.toString().contains("(null)")){
			driver = new FirefoxDriver();
		}
		return driver;
	}
	
	public static WebDriver initChromeDriver(){
		if(driver == null || driver.toString().contains("(null)")){
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}
	
	public static BrowserManager getInstance(){
		if(instance == null)
			instance = new BrowserManager();
		return instance;
	}
}
