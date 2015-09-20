package org.rm.automation.admin.pageobjects.conferenceRooms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.locators.conferenceRooms.ConferenceRoomsLocators;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.utils.Waiters;

public class ConferenceRoomsPage extends HomePage{
	
	@FindBy(xpath = ConferenceRoomsLocators.EnabledColumnBtnLocator)
	public WebElement enabledColumnBtn;
	
	@FindBy(xpath = ConferenceRoomsLocators.OutOfOrderColumnBtnLocator)
	public WebElement outOfOrderColumnBtn;
	
	@FindBy(xpath = ConferenceRoomsLocators.RoomColumnBtnLocator)
	public WebElement roomColumnBtn;
	
	@FindBy(xpath = ConferenceRoomsLocators.ConferenceRoomLocator)
	public WebElement conferenceRoom;
	
	@FindBy(xpath = ConferenceRoomsLocators.TotalItemsLabelLocator)
	public WebElement totalItemsLabel;
	
	@FindBy(xpath = ConferenceRoomsLocators.ConferenceRoomInfoLabelLocator)
	public WebElement conferenceRoomInfoLabel;
	
	@FindBy(xpath = ConferenceRoomsLocators.RoomsContainerLocator)
	public WebElement roomsContainer;
	
	@FindBy(xpath = ConferenceRoomsLocators.ResourceContainerLocator)
	public WebElement resourceContainer; 
	
	//
	//
	// temporarily out of order
	//
	@FindBy(xpath = "//span[@popover-title='Temporarily Out of Order']")
	WebElement OutOOrdericonOrder; 
	
	@FindBy(xpath = "//span[@popover-title='Closed for maintenance']")
	WebElement OutOOrdericonmaintenance; 
	
	@FindBy(xpath = "//span[@popover-title='Closed for reparations']")
	WebElement OutOOrdericonreparations; 
	
	@FindBy(xpath = "//span[@popover-title='Reserved for a special event']")
	WebElement OutOOrdericonevent; 
	

	
	//popover-title="Temporarily Out of Order"
	
	/*
	 * ------------------------------------------------------------------------------571---------------------------------------
	 */

	/*
	 * ------------------------------------------------------------------------------571---------------------------------------
	 */
	public ConferenceRoomsPage (WebDriver driver){
		super(driver);
		PageFactory.initElements(super.driver, this);
	}
	
	public List<WebElement> getResources(){
		Waiters.WaitByVisibilityOfWebElement(resourceContainer, driver);
//		(new WebDriverWait(driver, 20))
//		.until(ExpectedConditions.visibilityOf(resourceContainer));
		List<WebElement> list = resourceContainer.findElements(By.xpath("//span[@ng-model='resource.isSelected']"));
		
		return list;
	}
	
	public ConferenceRoomsPage clickOnResource(String resourceName){
		ConferenceRoomsPage res = null; 
		
		List<WebElement> list = getResources();
		for(WebElement webElement : list){
			Waiters.WaitByVisibilityOfWebElement(webElement, driver);
			if(webElement.getText().equals(resourceName)){
				webElement.click();
				res = this;
				break;
			}
		}
		
		return res;
	}
	
	public boolean isAssociatedToResource(String resourcename, String roomName){
		boolean res = false;
		
		WebElement room = this.getConferenceRoom(roomName);

		List<WebElement> list = room.findElements(By.xpath(".//div[@class='animate-if ng-scope']"));
		for(WebElement webElement : list){
			Waiters.WaitByVisibilityOfWebElement(webElement, driver);
			if(webElement.getAttribute("ng-if").toString().contains(resourcename)){
				res = true;
				break;
			}
		}
		
		return res;
	}
	
	public ConferenceRoomsPage clickEnabledColumnBtn(){
		Waiters.WaitByVisibilityOfWebElement(enabledColumnBtn, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(enabledColumnBtn));
		enabledColumnBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}
	
	public ConferenceRoomsPage clickOutOfOrderColumnBtn(){
		Waiters.WaitByVisibilityOfWebElement(outOfOrderColumnBtn, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(outOfOrderColumnBtn));
		outOfOrderColumnBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}

	public ConferenceRoomsPage clickRoomColumnBtn(){
		Waiters.WaitByVisibilityOfWebElement(roomColumnBtn, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(roomColumnBtn));
		roomColumnBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}
	
	/*
	 * It should receive the conference room name. -----lufer: message if this not has any function should be delete
	 */
	public RoomInfoPage doubleClickConferenceRoom(){
		Waiters.WaitByVisibilityOfWebElement(conferenceRoom, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(conferenceRoom));
		Actions builder = new Actions(driver);
		builder.doubleClick(conferenceRoom).perform();
		
		return new RoomInfoPage(driver);
	}

	public RoomInfoPage doubleClickConferenceRoom(String roomName){
		RoomInfoPage res = null; 
		
		Waiters.WaitByVisibilityOfWebElement(roomsContainer, driver);
//		roomsContainer = new WebDriverWait(driver, 20)
//			.until(ExpectedConditions.visibilityOf(roomsContainer)); // //span[@class='ng-binding']
		List<WebElement> list = roomsContainer.findElements(By.xpath(".//span[@class='ng-binding']"));// The span that contains the conference rooms.
		for(WebElement webElement : list){
			Waiters.WaitByVisibilityOfWebElement(webElement, driver);
			if(webElement.getText().equals(roomName)){
				Actions builder = new Actions(driver);
				builder.doubleClick(webElement).perform();
				res = new RoomInfoPage(driver);
				break;
			}
		}
		
		return res;
	}
	
	private WebElement getConferenceRoom(String roomName){
		WebElement res = null; 
		
		Waiters.WaitByVisibilityOfWebElement(roomsContainer, driver);
//		roomsContainer = new WebDriverWait(driver, 20)
//			.until(ExpectedConditions.visibilityOf(roomsContainer));
		List<WebElement> list = roomsContainer.findElements(By.xpath(".//div[@ng-style='rowStyle(row)']"));// The span that contains the conference rooms.
		for(WebElement webElement : list){
			Waiters.WaitByVisibilityOfWebElement(webElement, driver);
			WebElement roomLabel = webElement.findElement(By.xpath(".//span[@class='ng-binding']"));
			Waiters.WaitByVisibilityOfWebElement(roomLabel, driver);
//			roomLabel = new WebDriverWait(driver, 20)
//					.until(ExpectedConditions.visibilityOf(roomLabel));
			if(roomLabel.getText().equals(roomName)){
				res = webElement;
			}
		}
		
		return res;
	}

	public boolean isValidRoom(String roomName){
		boolean res = false; 
		
		Waiters.WaitByVisibilityOfWebElement(roomsContainer, driver);
		List<WebElement> list = roomsContainer.findElements(By.xpath("//span[@class='ng-binding']"));// The span that contains the conference room.
		for(WebElement webElement : list){
			Waiters.WaitByVisibilityOfWebElement(webElement, driver);
			if(webElement.getText().equals(roomName)){
				res = true;
			}
		}
		
		return res;
	}
	
	public String getTotalItemsLabelValue(){
		Waiters.WaitByVisibilityOfWebElement(totalItemsLabel, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(totalItemsLabel));
		
		return totalItemsLabel.getText();
	}
	
	public String getConferenceRoomInfoLabel(){
		Waiters.WaitByVisibilityOfWebElement(conferenceRoomInfoLabel, driver);
//		(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.visibilityOf(conferenceRoomInfoLabel));
		
		return conferenceRoomInfoLabel.getText();
	}
	
	/*
	 * pass the out of order title
	 * 
	 * 
	 * 
	 * falta metodooooooooooooooooooooooo
	 * */
	public boolean IsvisibleOOOIcon(String ooftext)
	{
		/*Temporarily Out of Order

		Closed for maintenance

		Closed for reparations

		Reserved for a special event*/ 
		
		/*theRoomsContainer = new WebDriverWait(driver, 20)
			.until(ExpectedConditions.visibilityOf(theRoomsContainer)); // //span[@class='ng-binding']
		List<WebElement> list = theRoomsContainer.findElements(By.xpath(".//span[@class='ng-binding']"));// The span that contains the conference rooms.
*/		
		if("Temporarily Out of Order".contains(ooftext)){
			OutOOrdericonOrder.isDisplayed();
			return true;
		}
		if("Closed for maintenance".contains(ooftext)){
			OutOOrdericonmaintenance.isDisplayed();
			return true;
		}
		if("Closed for reparations".contains(ooftext)){
			OutOOrdericonreparations.isDisplayed();
			return true;
		}
		if("Reserved for a special event".contains(ooftext)){
			OutOOrdericonevent.isDisplayed();
			return true;
		}	
		return false;
	}
}
