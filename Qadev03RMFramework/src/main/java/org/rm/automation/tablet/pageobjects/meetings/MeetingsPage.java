package org.rm.automation.tablet.pageobjects.meetings;

import java.util.List;

import org.openqa.selenium.By;
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
	
	protected final String txtboxOrganizer="txtOrganizer";
	protected final String txtboxSubject="txtSubject";
	protected final String txtboxAttendees="(//input[@type='text'])[3]";
	protected final String txtboxBody="txtBody";
	protected final String txtboxStart="//input[@type='time']";
	protected final String txtboxEnd="(//input[@type='time'])[2]";
	protected final String txtboxPassword="//input[@type='password']";
	protected final String bttnCreate="//button[@class='clean item item-btn']";
	protected final String bttnUpdate="//button[contains(.,'Update')]";
	protected final String bttnRemove="//button[contains(.,'Remove')]";	
	protected final String bttnSave="//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]";
	protected final String bttnSaveDelete="//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]";
	protected final String dvMeeting="//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div";
	protected final String dvAdvice="//div[@class='ng-binding ng-scope']";
	protected final String dvRoomName="//html/body/div[2]/div/div/div[1]/div[2]/div[2]";
	protected final String icnRemoveAttendee="//i[contains(@ng-click,'remove($index)')]";
	protected final String spnAttendee="//span[@class='rm-tag-elem ng-binding']";
	protected final String spnTime="//span[@ng-bind='currentTime']";
	protected final String spnScheduleTitle="//span[contains(.,'Schedule')]";
	
	
	@FindBy	(id=txtboxOrganizer)
	WebElement tbOrganizer;	
	@FindBy	(id=txtboxSubject)
	WebElement tbSubject;	
	@FindBy(xpath=txtboxAttendees)
	WebElement tbAttendees;	
	@FindBy	(id=txtboxBody)
	WebElement tbBody;	
	@FindBy	(xpath=txtboxStart)
	WebElement tbStartTime;	
	@FindBy	(xpath=txtboxEnd)
	WebElement tbEndTime;	
	@FindBy	(xpath=bttnCreate)
	WebElement btnCreate;	
	@FindBy	(xpath=txtboxPassword)
	WebElement tbPassword;	
	@FindBy	(xpath=bttnSave)
	WebElement btnConfirmMeeting;	
	@FindBy	(xpath=bttnSaveDelete)
	WebElement btnConfirmDeleteMeeting;	
	@FindBy	(xpath=dvMeeting)
	WebElement divMeeting;	
	@FindBy	(xpath=bttnUpdate)
	WebElement btnUpdate;
	@FindBy	(xpath=bttnRemove)
	WebElement btnRemove;	
	@FindBy	(xpath=dvAdvice)
	WebElement divMeetingAdvice;	
	@FindBy	(xpath=dvRoomName)
	WebElement divRoomName;
	@FindBy(xpath=icnRemoveAttendee)
	WebElement iconRemoveAttendee;
	@FindBy(xpath=spnAttendee)
	WebElement spanAttendee;
	@FindBy(xpath=spnTime)
	WebElement spanTime;
	@FindBy(xpath=spnScheduleTitle)
	WebElement spanSchedulePageTitle;
	
	private WebDriver driver;
	
	public MeetingsPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	public MeetingsPage setOrganizer(String name){	
		LogManager.info("MeetingsPage : Typing the organizer's account username");
		Waiters.WaitById(txtboxOrganizer,driver);		
		tbOrganizer.sendKeys(name);
		return this;
	}
	
	public MeetingsPage setSubject(String subject){
		LogManager.info("MeetingsPage : Typing the meeting subject");
		Waiters.WaitById(txtboxSubject,driver);		
		tbSubject.clear();
		tbSubject.sendKeys(subject);
		return this;
	}
	
	public MeetingsPage setAtendees(String name){
		LogManager.info("MeetingsPage : Typing adding the attendees accounts");
		Waiters.WaitByXPath(txtboxAttendees,driver);
		tbAttendees.clear();
		tbAttendees.sendKeys(name);
		tbAttendees.sendKeys(Keys.ENTER);
		return this;
	}
	
	public MeetingsPage setBody(String body){
		LogManager.info("MeetingsPage : Typing the meetings body (optional field)");
		Waiters.WaitById(txtboxBody,driver);
		tbBody.clear();
		tbBody.sendKeys(body);		
		return this;
	}
	
	public MeetingsPage setDates(String begin, String end){
		LogManager.info("MeetingsPage : Typing the begin and end time of the meeting");
		Waiters.WaitByXPath(txtboxStart,driver);		
		tbStartTime.clear();
		tbStartTime.sendKeys(begin);
		
		Waiters.WaitByXPath(txtboxEnd,driver);
		tbEndTime.clear();
		tbEndTime.sendKeys(end);		
		return this;
	}
	
	public MeetingsPage confirmMeeting(){
		LogManager.info("MeetingsPage : Clicking on Create button to confirm the meeting creation");
		Waiters.WaitByXPath(bttnCreate,driver);
		btnCreate.click();
		return this;
	}
	
	public MeetingsPage confirmUser(String password){
		LogManager.info("MeetingsPage : Typing the organizer's account password");
		Waiters.WaitByXPath(txtboxPassword,driver);
		tbPassword.clear();
		tbPassword.sendKeys(password);
		return this;
	}
	
	public MeetingsPage saveMeeting(){
		LogManager.info("MeetingsPage : Saving the meeting");
		Waiters.WaitByXPath(bttnSave,driver);
		btnConfirmMeeting.click();		
		return this;
	}
	
	public MeetingsPage saveDeleteMeeting(){
		LogManager.info("MeetingsPage : Deleting the meeting");
		Waiters.WaitByXPath(bttnSaveDelete,driver);
		btnConfirmDeleteMeeting.click();
		
		return this;
	}
	
	public MeetingsPage selectMeeting(){
		Waiters.WaitByXPath(dvMeeting,driver);
		divMeeting.click();
		return this;
	}
	
	public MeetingsPage updateMeeting(){
		LogManager.info("MeetingsPage : Clicking Update button");
		Waiters.WaitByXPath(bttnUpdate,driver);
		btnUpdate.click();		
		return this;
	}
	
	public MeetingsPage deleteMeeting(){
		LogManager.info("MeetingsPage : Clicking Remove button");
		Waiters.WaitByXPath(bttnRemove,driver);
		btnRemove.click();		
		return this;
	} 
	
	public String meetingAdvices(){		
		String result = "";
		LogManager.info("MeetingsPage : Verifying if the meeting has perform an action");
		Waiters.WaitByXPath(dvAdvice,driver);		
		result = divMeetingAdvice.getText();		
		return result;
	}
	
	public boolean isTheRightRoom(String roomName){
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		boolean result = false;
		LogManager.info("MeetingsPage : Verifying if the current room is the selected by the user");
		Waiters.WaitByXPath(dvRoomName,driver);		
		result = divRoomName.getText().equals(roomName)?true:false;	
		return result;
	}
	
	public MeetingsPage removeAttendee(){
		LogManager.info("MeetingsPage : Removing an attendee");
		Waiters.WaitByXPath(icnRemoveAttendee,driver);
		iconRemoveAttendee.click(); 
		return this;
	}	
	
	public boolean isAttendeeAdded(String attendeeName)
	{		
		boolean result = false;
		LogManager.info("MeetingsPage : Verifying than the attendee has been removed");
		Waiters.WaitByXPath(spnAttendee,driver);		
		result = (spanAttendee.isDisplayed() && spanAttendee.getText().equals(attendeeName))? true : false;
		return result;
	} 
	
	public List<WebElement> attendeeRemoved(){
		LogManager.info("MeetingsPage : Verifying than the attendee has been removed");		
		List<WebElement> attendees = driver.findElements(By.xpath(spnAttendee));				
		return attendees;
	}
	
	public boolean checkTime(){		
		boolean result = false;
		String currentTime = RoomManagerTime.currenTime();
		LogManager.info("MeetingsPage : Verifying than the current time is the same than RoomManager has");
		Waiters.WaitByXPath(spnTime, driver);
		result = spanTime.getText().equals(currentTime)?true:false;
		return result;
	}	
	
	public MeetingsPage checkIfIconRedirectsToSchedulePage(){
		LogManager.info("MeetingsPage : Verifying than the schedule icon redirects to the Schedule page");
		Waiters.WaitByXPath(spnScheduleTitle, driver);		
		Assert.assertEquals("Schedule",spanSchedulePageTitle.getText());
		return this;
	}
}
