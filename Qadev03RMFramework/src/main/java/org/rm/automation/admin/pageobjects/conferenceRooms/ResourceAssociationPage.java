package org.rm.automation.admin.pageobjects.conferenceRooms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResourceAssociationPage extends ConferenceRoomCommonPage{
	
	@FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/div/div[2]/div[1]/legend")
	public WebElement availableLabel;
	
	@FindBy(xpath = "//div[@class='col-xs-12 col-sm-5 col-lg-5']")
	public WebElement availableResourcesContainer;
	
//	@FindBy(xpath = "//button[@ng-click='save()']")
	@FindBy(xpath = "//div[4]/div/div/div[3]/div[2]/button")
	public WebElement saveButton;
	
	public ResourceAssociationPage(WebDriver driver){
		super(driver);
	}
	
	public String getAvailablelabel(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(availableLabel));
		
		return availableLabel.getText();
	}
	
	public List<WebElement> getAvailableResources(){
		(new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(availableResourcesContainer));
		List<WebElement> list = availableResourcesContainer.findElements(By.xpath("//div[@ng-repeat='availableResource in availableResources']"));
		
		return list;
	}
	
	public ResourceAssociationPage associateResource(String resourceAssociatedName){
		ResourceAssociationPage res = null;
		
		List<WebElement> list = this.getAvailableResources();
		for(WebElement webElement : list){
			if(webElement.findElement(By.xpath(".//span[@class='ng-binding']")).getText().equals(resourceAssociatedName)){
				webElement.findElement(By.xpath(".//button[@class='btn-clear']")).click();
				res = this;
				break;
			}
		}
		
		return res;
	}

	public ConferenceRoomsPage clickSaveButton(){
		saveButton = (new WebDriverWait(driver, 20))
				  .until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		
		return new ConferenceRoomsPage(driver);
	}
}
