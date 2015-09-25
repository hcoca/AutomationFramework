package org.rm.automation.tablet.pageobjects.meetings;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.TabletPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.RoomManagerTime;
import org.rm.automation.utils.Waiters;
import org.testng.Assert;

public class MeetingsPage extends TabletPage{
	
	@FindBy	(id="txtOrganizer")
	WebElement tbOrganizer;	
	@FindBy	(id="txtSubject")
	WebElement tbSubject;	
	@FindBy(xpath="(//input[@type='text'])[3]")
	WebElement tbAttendees;	
	@FindBy	(id="txtBody")
	WebElement tbBody;	
	@FindBy	(xpath="//input[@type='time']")
	WebElement tbStartTime;	
	@FindBy	(xpath="(//input[@type='time'])[2]")
	WebElement tbEndTime;	
	@FindBy	(xpath="//button[@class='clean item item-btn']")
	WebElement btnCreate;	
	@FindBy	(xpath="//input[@type='password']")
	WebElement tbPassword;	
	@FindBy	(xpath="//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]")
	WebElement btnConfirmMeeting;	
	@FindBy	(xpath="//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]")
	WebElement btnConfirmDeleteMeeting;	
	@FindBy	(xpath="//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div")
	WebElement divMeeting;	
	@FindBy	(xpath="//button[contains(.,'Update')]")
	WebElement btnUpdate;
	@FindBy	(xpath="//button[contains(.,'Remove')]")
	WebElement btnRemove;	
	@FindBy	(xpath="//div[@class='ng-binding ng-scope']")
	WebElement divMeetingAdvice;	
	@FindBy	(xpath="//html/body/div[2]/div/div/div[1]/div[2]/div[2]")
	WebElement divRoomName;
	@FindBy(xpath="//i[contains(@ng-click,'remove($index)')]")
	WebElement iconRemoveAttendee;
	@FindBy(xpath="//span[@class='rm-tag-elem ng-binding']")
	WebElement spanAttendee;
	@FindBy(xpath="//span[@ng-bind='currentTime']")
	WebElement spanTime;
	@FindBy(xpath="//span[contains(.,'Schedule')]")
	WebElement spanSchedulePageTitle;
	
	private WebDriver driver;
	
	public MeetingsPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	public MeetingsPage setOrganizer(String name){	
		LogManager.info("MeetingsPage : Typing the organizer's account username");
		Waiters.WaitById("txtOrganizer",driver);		
		tbOrganizer.sendKeys(name);
		return this;
	}
	
	public MeetingsPage setSubject(String subject){
		LogManager.info("MeetingsPage : Typing the meeting subject");
		Waiters.WaitById("txtSubject",driver);		
		tbSubject.clear();
		tbSubject.sendKeys(subject);
		return this;
	}
	
	public MeetingsPage setAtendees(String name){
		LogManager.info("MeetingsPage : Typing adding the attendees accounts");
		Waiters.WaitByXPath("(//input[@type='text'])[3]",driver);
		tbAttendees.clear();
		tbAttendees.sendKeys(name);
		tbAttendees.sendKeys(Keys.ENTER);
		return this;
	}
	
	public MeetingsPage setBody(String body){
		LogManager.info("MeetingsPage : Typing the meetings body (optional field)");
		Waiters.WaitById("txtBody",driver);
		tbBody.clear();
		tbBody.sendKeys(body);		
		return this;
	}
	
	public MeetingsPage setDates(String begin, String end){
		LogManager.info("MeetingsPage : Typing the begin and end time of the meeting");
		Waiters.WaitByXPath("//input[@type='time']",driver);		
		tbStartTime.clear();
		tbStartTime.sendKeys(begin);
		
		Waiters.WaitByXPath("(//input[@type='time'])[2]",driver);
		tbEndTime.clear();
		tbEndTime.sendKeys(end);		
		return this;
	}
	
	public MeetingsPage confirmMeeting(){
		LogManager.info("MeetingsPage : Clicking on Create button to confirm the meeting creation");
		Waiters.WaitByXPath("//button[@class='clean item item-btn']",driver);
		btnCreate.click();
		return this;
	}
	
	public MeetingsPage confirmUser(String password){
		LogManager.info("MeetingsPage : Typing the organizer's account password");
		Waiters.WaitByXPath("//input[@type='password']",driver);
		tbPassword.clear();
		tbPassword.sendKeys(password);
		return this;
	}
	
	public MeetingsPage saveMeeting(){
		LogManager.info("MeetingsPage : Saving the meeting");
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]",driver);
		btnConfirmMeeting.click();		
		return this;
	}
	
	public MeetingsPage saveDeleteMeeting(){
		LogManager.info("MeetingsPage : Deleting the meeting");
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]",driver);
		btnConfirmDeleteMeeting.click();
		
		return this;
	}
	
	public MeetingsPage selectMeeting(){
		Waiters.WaitByXPath("//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div",driver);
		divMeeting.click();
		return this;
	}
	
	public MeetingsPage updateMeeting(){
		LogManager.info("MeetingsPage : Clicking Update button");
		Waiters.WaitByXPath("//button[contains(.,'Update')]",driver);
		btnUpdate.click();		
		return this;
	}
	
	public MeetingsPage deleteMeeting(){
		LogManager.info("MeetingsPage : Clicking Remove button");
		Waiters.WaitByXPath("//button[contains(.,'Remove')]",driver);
		btnRemove.click();		
		return this;
	} 
	
	public MeetingsPage meetingAdvices(String expect){		
		LogManager.info("MeetingsPage : Verifying if the meeting has perform an action");
		Waiters.WaitByXPath("//div[@class='ng-binding ng-scope']",driver);		
		Assert.assertEquals(expect,divMeetingAdvice.getText());		
		return this;
	}
	
	public MeetingsPage isTheRightRoom(String roomName){
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		LogManager.info("MeetingsPage : Verifying if the current room is the selected by the user");
		Waiters.WaitByXPath("//html/body/div[2]/div/div/div[1]/div[2]/div[2]",driver);		
		Assert.assertEquals(roomName, divRoomName.getText());	
		return this;
	}
	
	public MeetingsPage removeAttendee(){
		LogManager.info("MeetingsPage : Removing an attendee");
		Waiters.WaitByXPath("//i[contains(@ng-click,'remove($index)')]",driver);
		iconRemoveAttendee.click(); 
		return this;
	}
	
	public MeetingsPage attendeeAdded(){
		LogManager.info("MeetingsPage : Verifying than the attendee has been removed");
		Waiters.WaitByXPath("//span[@class='rm-tag-elem ng-binding']",driver);
		Assert.assertEquals(true,spanAttendee.isDisplayed());
		Assert.assertEquals(true,iconRemoveAttendee.isDisplayed());
		return this;
	}
	
	public MeetingsPage attendeeRemoved(){
		LogManager.info("MeetingsPage : Verifying than the attendee has been removed");		
		Assert.assertEquals(false,spanAttendee.isDisplayed());
		Assert.assertEquals(false,iconRemoveAttendee.isDisplayed());
		return this;
	}
	
	public MeetingsPage checkTime(){		
		String currentTime = RoomManagerTime.currenTime();
		LogManager.info("MeetingsPage : Verifying than the current time is the same than RoomManager has");
		Waiters.WaitByXPath("//span[@ng-bind='currentTime']", driver);
		Assert.assertEquals(currentTime, spanTime.getText());
		return this;
	}	
	
	public MeetingsPage checkIfIconRedirectsToSchedulePage(){
		LogManager.info("MeetingsPage : Verifying than the schedule icon redirects to the Schedule page");
		Waiters.WaitByXPath("//span[contains(.,'Schedule')]", driver);		
		Assert.assertEquals("Schedule",spanSchedulePageTitle.getText());
		return this;
	}
}
