package org.rm.automation.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserManager {

	public WebDriver driver;
	private static BrowserManager instance = null;
		
	protected BrowserManager() {
		String baseUrl = "http://172.20.208.79:4040/admin/#/admin";
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}
	public static BrowserManager getInstance() {
      if(instance == null) {
         instance = new BrowserManager();
      }
      return instance;
	}
   
	public WebDriver getBrowser()
	{
		return this.driver;
	}
	
	public void setBrowser(WebDriver d)
	{
		this.driver = d;
	}
	
	public void exit()
	{
		this.driver.quit();
		instance = null;
	}
}
