package org.rm.automation.admin.pageobjects.impersonation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.Waiters;

import junit.framework.Assert;

public class ImpersonationPage extends HomePage{
	
	private WebDriver driver;
	
	@FindBy(xpath="//span[contains(.,'Use Impersonation')]")
	WebElement useImpersonationCB;
	
	@FindBy(xpath="//span[@class='radio-label']")
	WebElement useImpersonationCredentialsRB;
	
	@FindBy(xpath="//button[contains(.,'Save')]")
	WebElement useImpersonationSave;	
	
	@FindBy(xpath="//div[contains(.,'×Impersonation is now enabled.')]")
	WebElement impersonationEnabled;
	
	@FindBy(xpath="//div[contains(.,'Impersonation is now disabled.')]")
	WebElement impersonationDisabled;

	public ImpersonationPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	public ImpersonationPage clickImpersonationCheckbox(){
		LogManager.info("ImpersonationPage: Clicking the checkbox to enable impersonation ");
		Waiters.WaitByXPath("//span[contains(.,'Use Impersonation')]", driver);
		if(useImpersonationCB.isDisplayed())
			useImpersonationCB.click();
		return this;
	}
	
	public ImpersonationPage clickCredentialsRadioButton(){
		Waiters.WaitByXPath("//span[@class='radio-label']", driver);
		if(useImpersonationCredentialsRB.isDisplayed())
			useImpersonationCredentialsRB.click();
		return this;
	}
	
	public ImpersonationPage saveChanges(){
		Waiters.WaitByXPath("//button[contains(.,'Save')]", driver);
		if(useImpersonationSave.isDisplayed())
			useImpersonationSave.click();
		return this;
	}
	
	public ImpersonationPage impersonationIsEnabled(){
		Waiters.WaitByXPath("//div[contains(.,'×Impersonation is now enabled.')]", driver);
		if(impersonationEnabled.isDisplayed())
			Assert.assertEquals(true, impersonationEnabled.isDisplayed());
		return this;
	}
	
	public ImpersonationPage impersonationIsDisabled(){
		Waiters.WaitByXPath("//div[contains(.,'Impersonation is now disabled.')]", driver);
		if(impersonationDisabled.isDisplayed())
			Assert.assertEquals(true, impersonationDisabled.isDisplayed());
		return this;
	}
}
