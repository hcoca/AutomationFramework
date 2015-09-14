package org.rm.automation.admin.pageobjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;

public class LoginPage {
	
	Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");	
	private WebDriver driver;
	@FindBy(id="loginUsername")
	  WebElement usernameTextBox;
	@FindBy(id="loginPassword")
	  WebElement passwordTextBox;
	@FindBy(xpath="//button[@class='btn btn-primary pull-right']")
	  WebElement loginBtn;
	public LoginPage() {}
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		driver.get(settings.getProperty("url_adminConsole"));
		PageFactory.initElements(driver, this);
	}
	public void enterUserName(String userName) {
		if(usernameTextBox.isDisplayed())
		{
			LogManager.info("LoginPage: Entering the username to sign in");
			usernameTextBox.sendKeys(userName);
		}
	}
	
	public void enterPassword(String password) {
		if(passwordTextBox.isDisplayed())
		{
			LogManager.info("LoginPage: Entering the password to sign in");
			passwordTextBox.sendKeys(password);
		}
	}
	
	public void clickOnSignIn() {
		if(loginBtn.isDisplayed())
		{
			LogManager.info("LoginPage: Logging in the application");
			loginBtn.click();
		}
	}
	public HomePage SignIn(String user, String pwd) {
		LogManager.info("LoginPage: Entering the credentials");
		enterUserName(user);
		enterPassword(pwd);
		clickOnSignIn();		
		return new HomePage(driver);
	}
}
