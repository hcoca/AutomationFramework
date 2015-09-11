package org.rm.automation.admin.pageobjects.emailServers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.utils.LogManager;

public class RemoveServerpage {
	
	  WebDriver driver;

	  @FindBy(xpath="//div[2]/div/div/span")
	  WebElement popdelete;
	  
	  @FindBy(xpath="//button[@ng-click='onYes()']")
	  WebElement btnyesdelete;
	  
	  @FindBy(xpath="//button[@ng-click='onNo()']")
	  WebElement btnnodelete;
	  
	  public RemoveServerpage(WebDriver driver)
	  {
		this.driver = driver;
		LogManager.info(" -- > open dilog delete");
		PageFactory.initElements(driver, this);
	  }

	  // this should be return "Do you really want to delete the current Email Server and all its associated elements?"
	  public String returntextDelete()
	  {
		  return popdelete.getText();
	  }
	  public EmailServersPage yesdelete(){
		  LogManager.info(" -- > click acept button remove server");
		  btnyesdelete.click();
		  return new EmailServersPage(driver);
	  }
	  public EmailServersPage nodelte() {
		  LogManager.info("--> click no delete");
		btnnodelete.click();
		return new EmailServersPage(driver);
	  }
}
