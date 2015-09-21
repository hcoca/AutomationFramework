package org.rm.automation.admin.pageobjects.conferenceRooms;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rm.automation.admin.locators.conferenceRooms.ConferenceRoomsLocators;
import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.locations.IssuesPage;
import org.rm.automation.utils.LogManager;
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
	
	public int getNumberOfRoomsFromContainer(){
		int res = 0;
		
		Waiters.WaitByVisibilityOfWebElement(roomsContainer, driver);
		List<WebElement> list = roomsContainer.findElements(By.xpath(".//div[@ng-style='rowStyle(row)']"));
		if(list.size() > 0 ){
			res = list.size();
		}
		
		return res;
	}
	
	public int getNumberOfRoomsFromLabel(){
		int res = 0;
		
		Waiters.WaitByVisibilityOfWebElement(totalItemsLabel, driver);
		try {
			res = Integer.parseInt(totalItemsLabel.getText().substring(13));
		} catch (Exception e) {
			LogManager.info("ConferenceRoomPage: Error parsing to integer");
		}
		
		return res;
	}
	
	public List<WebElement> getResources(){
		Waiters.WaitByVisibilityOfWebElement(resourceContainer, driver);
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
		enabledColumnBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}
	
	public ConferenceRoomsPage clickOutOfOrderColumnBtn(){
		Waiters.WaitByVisibilityOfWebElement(outOfOrderColumnBtn, driver);
		outOfOrderColumnBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}

	public ConferenceRoomsPage clickRoomColumnBtn(){
		Waiters.WaitByVisibilityOfWebElement(roomColumnBtn, driver);
		roomColumnBtn.click();
		
		return new ConferenceRoomsPage(driver);
	}

	public RoomInfoPage doubleClickConferenceRoom(String roomName){
		RoomInfoPage res = null; 
		
		Waiters.WaitByVisibilityOfWebElement(roomsContainer, driver);
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
		List<WebElement> list = roomsContainer.findElements(By.xpath(".//div[@ng-style='rowStyle(row)']"));
		for(WebElement webElement : list){
			Waiters.WaitByVisibilityOfWebElement(webElement, driver);
			WebElement roomLabel = webElement.findElement(By.xpath(".//span[@class='ng-binding']"));
			Waiters.WaitByVisibilityOfWebElement(roomLabel, driver);
			if(roomLabel.getText().equals(roomName)){
				res = webElement;
			}
		}
		
		return res;
	}

	public boolean isValidRoom(String roomName){
		boolean res = false; 
		
		Waiters.WaitByVisibilityOfWebElement(roomsContainer, driver);
		List<WebElement> list = roomsContainer.findElements(By.xpath("//span[@class='ng-binding']"));
		for(WebElement webElement : list){
			Waiters.WaitByVisibilityOfWebElement(webElement, driver);
			if(webElement.getText().equals(roomName)){
				res = true;
				break;
			}
		}
		
		return res;
	}
	
	public boolean isEnabledRoom(String roomName){
		boolean res = false;
		
		Waiters.WaitByVisibilityOfWebElement(roomsContainer, driver);
		List<WebElement> list = roomsContainer.findElements(By.xpath("//span[@ng-show='row.entity.enabled']"));
		for(WebElement webElement : list){
			if(webElement.getText().equals(roomName)){
				res = true;
				break;
			}
		}
		
		return res;
	}
	
	public boolean isCodeUpdated(String updatedCode, String roomName){
		boolean res = false;
		
		IssuesPage issuesPage = this.SelectIssuesOption();
		ConferenceRoomsPage conferenceRoom = issuesPage.SelectRoomsOption();
		RoomInfoPage roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		if(roomInfo.getCode().equals(updatedCode)){
			res = true;
		}
		
		return res;
	}
	
	public boolean isCapacityUpdated(String updatedCapacity, String roomName){
		boolean res = false;
		
		IssuesPage issuesPage = this.SelectIssuesOption();
		ConferenceRoomsPage conferenceRoom = issuesPage.SelectRoomsOption();
		RoomInfoPage roomInfo = conferenceRoom.doubleClickConferenceRoom(roomName);
		if(roomInfo.getCapacity().equals(updatedCapacity)){
			res = true;
		}
		
		return res;
	}
	
	public String getTotalItemsLabelValue(){
		Waiters.WaitByVisibilityOfWebElement(totalItemsLabel, driver);
		
		return totalItemsLabel.getText();
	}
	
	public String getConferenceRoomInfoLabel(){
		Waiters.WaitByVisibilityOfWebElement(conferenceRoomInfoLabel, driver);
		
		return conferenceRoomInfoLabel.getText();
	}
	
	/*
	 * pass the out of order title
	 * 
	 *  metodo Out of order
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
