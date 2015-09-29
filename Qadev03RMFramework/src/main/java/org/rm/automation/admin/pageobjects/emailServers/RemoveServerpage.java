
package org.rm.automation.admin.pageobjects.emailServers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.locators.emailServers.RemoveServerPageLocator;
import org.rm.automation.utils.LogManager;

public class RemoveServerpage {
	
	  WebDriver driver;

	  @FindBy(xpath=RemoveServerPageLocator.popdeleteLocator)
	  WebElement popdelete;
	  
	  @FindBy(xpath=RemoveServerPageLocator.btnyesdeleteLocator)
	  WebElement btnyesdelete;
	  
	  @FindBy(xpath=RemoveServerPageLocator.btnnodeleteLocator)
	  WebElement btnnodelete;
	  
	  public RemoveServerpage(WebDriver driver)
	  {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	  }

	  // this should be return "Do you really want to delete the current Email Server and all its associated elements?"
	  public String returntextDelete()
	  {
		  return popdelete.getText();
	  }
	  public EmailServersPage yesdelete(){
		  LogManager.info("RemoveServerPage: press yes button Confirm delete EmailServer");
		  btnyesdelete.click();
		  return new EmailServersPage(driver);
	  }
	  public EmailServersPage nodelte() {
		LogManager.info("RemoveServerPage: press no button cancel delete EmailServer");
		btnnodelete.click();
		return new EmailServersPage(driver);
	  }
}
