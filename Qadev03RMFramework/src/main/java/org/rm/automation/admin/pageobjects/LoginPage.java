package org.rm.automation.admin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rm.automation.utils.BrowserManager;

public class LoginPage {
	
	/*----------------------------------------------------*/
	private WebDriver driver;
	private By usernameTextBox = By.id("loginUsername");
	private By passwordTextBox = By.id("loginPassword");
	private By loginBtn = By.xpath("//button[@class='btn btn-primary pull-right']");
	public LoginPage() {}
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	public void enterUserName(String userName) {
		WebElement usernameTxtBox = driver.findElement(usernameTextBox);
		if(usernameTxtBox.isDisplayed())
			usernameTxtBox.sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		WebElement passwordTxtBox = driver.findElement(passwordTextBox);
		if(passwordTxtBox.isDisplayed())
			passwordTxtBox.sendKeys(password);
	}
	
	public void clickOnSignIn() {
		WebElement signInBtn = driver.findElement(loginBtn);
		if(signInBtn.isDisplayed())
			signInBtn.click();
	}
	public HomePage SignIn(String user, String pwd) {
		WebElement signInBtn = driver.findElement(loginBtn);
		enterUserName(user);
		enterPassword(pwd);
		clickOnSignIn();
		
		return new HomePage(driver);
	}
	/*------------------------------------------------------*/
	/*public LoginPage setUsername(String name)
	{
		WebElement username = BrowserManager.getInstance().getBrowser().findElement(By.id("loginUsername"));
		username.sendKeys(name);
				
		return this;
	}
	
	public LoginPage setPassword(String password)
	{
		WebElement pass = BrowserManager.getInstance().getBrowser().findElement(By.id("loginPassword"));
		pass.sendKeys(password);
		
		return this;
	}
	
	public HomePage login()
	{
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebElement login = BrowserManager.getInstance().getBrowser().findElement(By.xpath("//button[@type='submit']"));
		login.click();
		
		return new HomePage();
	}*/
}
