package org.rm.automation.admin.pageobjects.conferenceRooms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.locators.conferenceRooms.ResourceAssociationLocators;
import org.rm.automation.utils.Waiters;

public class ResourceAssociationPage extends ConferenceRoomCommonPage{
	
	@FindBy(xpath = ResourceAssociationLocators.AvailableLabelLocator)
	public WebElement availableLabel;
	
	@FindBy(xpath = ResourceAssociationLocators.AvailableResourcesContainerLocator)
	public WebElement availableResourcesContainer;
	
	@FindBy(xpath = ResourceAssociationLocators.SaveButtonLocator)
	public WebElement saveButton;
	
	public ResourceAssociationPage(WebDriver driver){
		super(driver);
	}
	
	public String getAvailablelabel(){
		Waiters.WaitByVisibilityOfWebElement(availableLabel, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(availableLabel));
		
		return availableLabel.getText();
	}
	
	public List<WebElement> getAvailableResources(){
		Waiters.WaitByVisibilityOfWebElement(availableResourcesContainer, driver);
//		(new WebDriverWait(driver, 20))
//		.until(ExpectedConditions.visibilityOf(availableResourcesContainer));
		List<WebElement> list = availableResourcesContainer.findElements(By.xpath("//div[@ng-repeat='availableResource in availableResources']"));
		
		return list;
	}
	
	public ResourceAssociationPage associateResource(String resourceAssociatedName){
		ResourceAssociationPage res = null;
		
		List<WebElement> list = this.getAvailableResources();
		for(WebElement webElement : list){
			Waiters.WaitByVisibilityOfWebElement(webElement, driver);
			WebElement resourceAvailablelabel = webElement.findElement(By.xpath(".//span[@class='ng-binding']"));
			Waiters.WaitByVisibilityOfWebElement(resourceAvailablelabel, driver);
			if(resourceAvailablelabel.getText().equals(resourceAssociatedName)){
				WebElement resourceAvailableButton = webElement.findElement(By.xpath(".//button[@class='btn-clear']"));
				Waiters.WaitByVisibilityOfWebElement(resourceAvailableButton, driver);
				resourceAvailableButton.click();
				res = this;
				break;
			}
		}
		
		return res;
	}

	public ConferenceRoomsPage clickSaveButton(){
		Waiters.WaitByVisibilityOfWebElement(saveButton, driver);
//		saveButton = (new WebDriverWait(driver, 20))
//				  .until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		
		return new ConferenceRoomsPage(driver);
	}
}
