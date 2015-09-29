package org.rm.automation.tablet.pageobjects.meetings;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rm.automation.tablet.pageobjects.TabletPage;
import org.rm.automation.utils.ReadPropertyValues;
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
	protected final String txtboxStartDate="//input[@ng-model='editable.from' and @type='date']";
	protected final String txtboxEndDate="//input[@ng-model='editable.to' and @type='date']";
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
	protected final String divErrMsSubject="//small[@ng-show='formErrors.title' and contains(.,'Subject is required')]";
	protected final String divErrMsOrganizer="//small[@ng-show='formErrors.organizer' and contains(.,'Organizer is required')]";
	protected final String divErrMsStartTime="//small[@ng-show='formErrors.intervalInvalid' and contains(.,'Start time must be smaller than end time')]";
	protected final String divConfirmUpdateMs="//div[contains(.,'Meeting successfully updated')]";
	
	private WebElement element;
	
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String server = settings.getProperty("server");
	private String port = settings.getProperty("port");
	private String url = "http://"+server+":"+port;
	
	@FindBy	(id=txtboxOrganizer)
	protected WebElement tbOrganizer;	
	@FindBy	(id=txtboxSubject)
	protected WebElement tbSubject;	
	@FindBy(xpath=txtboxAttendees)
	protected WebElement tbAttendees;	
	@FindBy	(id=txtboxBody)
	protected WebElement tbBody;	
	@FindBy	(xpath=txtboxStart)
	protected WebElement tbStartTime;	
	@FindBy	(xpath=txtboxEnd)
	WebElement tbEndTime;
	@FindBy	(xpath=txtboxStartDate)
	WebElement tbStartDate;	
	@FindBy	(xpath=txtboxEndDate)
	WebElement tbEndDate;
	@FindBy	(xpath=bttnCreate)
	protected WebElement btnCreate;	
	@FindBy	(xpath=txtboxPassword)
	protected WebElement tbPassword;	
	@FindBy	(xpath=bttnSave)
	protected WebElement btnConfirmMeeting;	
	@FindBy	(xpath=bttnSaveDelete)
	protected WebElement btnConfirmDeleteMeeting;	
	@FindBy	(xpath=dvMeeting)
	protected WebElement divMeeting;	
	@FindBy	(xpath=bttnUpdate)
	protected WebElement btnUpdate;
	@FindBy	(xpath=bttnRemove)
	protected WebElement btnRemove;	
	@FindBy	(xpath=dvAdvice)
	protected WebElement divMeetingAdvice;	
	@FindBy	(xpath=dvRoomName)
	protected WebElement divRoomName;
	@FindBy(xpath=icnRemoveAttendee)
	protected WebElement iconRemoveAttendee;
	@FindBy(xpath=spnAttendee)
	protected WebElement spanAttendee;
	@FindBy(xpath=spnTime)
	protected WebElement spanTime;
	@FindBy(xpath=spnScheduleTitle)
	protected WebElement spanSchedulePageTitle;
	@FindBy(xpath=divErrMsSubject)
	WebElement divErrMsjSubject;
	@FindBy(xpath=divErrMsOrganizer)
	WebElement divErrMsjOrganizer;
	@FindBy(xpath=divErrMsStartTime)
	WebElement divErrMsjStartTime;
	@FindBy(xpath=divConfirmUpdateMs)
	WebElement divConfirmUpdateMsj;
	
	private WebDriver driver;
	
	public MeetingsPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	public MeetingsPage setOrganizer(String name){	
		Waiters.WaitById(txtboxOrganizer,driver);		
		tbOrganizer.sendKeys(name);
		return this;
	}
	
	public MeetingsPage setSubject(String subject){
		Waiters.WaitById(txtboxSubject,driver);		
		tbSubject.clear();
		tbSubject.sendKeys(subject);
		return this;
	}
	
	public MeetingsPage setAtendees(String name){
		Waiters.WaitByXPath(txtboxAttendees,driver);
		tbAttendees.clear();
		tbAttendees.sendKeys(name);
		tbAttendees.sendKeys(Keys.ENTER);
		return this;
	}
	
	public MeetingsPage setBody(String body){
		Waiters.WaitById(txtboxBody,driver);
		tbBody.clear();
		tbBody.sendKeys(body);		
		return this;
	}
	
	public MeetingsPage setDates(String begin, String end){
		Waiters.WaitByXPath(txtboxStart,driver);		
		tbStartTime.clear();
		tbStartTime.sendKeys(begin);
		
		Waiters.WaitByXPath(txtboxEnd,driver);
		tbEndTime.clear();
		tbEndTime.sendKeys(end);		
		return this;
	}
	
	public MeetingsPage confirmMeeting(){
		Waiters.WaitByXPath(bttnCreate,driver);
		btnCreate.click();
		return this;
	}
	
	public MeetingsPage confirmUser(String password){
		Waiters.WaitByXPath(txtboxPassword,driver);
		tbPassword.clear();
		tbPassword.sendKeys(password);
		return this;
	}
	
	public MeetingsPage saveMeeting(){
		Waiters.WaitByXPath(bttnSave,driver);
		btnConfirmMeeting.click();		
		return this;
	}
	
	public MeetingsPage saveDeleteMeeting(){
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
		Waiters.WaitByXPath(bttnUpdate,driver);
		btnUpdate.click();		
		return this;
	}
	
	public MeetingsPage deleteMeeting(){
		Waiters.WaitByXPath(bttnRemove,driver);
		btnRemove.click();		
		return this;
	} 
	
	public String meetingAdvices(){		
		String result = "";
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
		Waiters.WaitByXPath(dvRoomName,driver);		
		result = divRoomName.getText().equals(roomName)?true:false;	
		return result;
	}
	
	public MeetingsPage removeAttendee(){
		Waiters.WaitByXPath(icnRemoveAttendee,driver);
		iconRemoveAttendee.click(); 
		return this;
	}	
	
	public boolean isAttendeeAdded(String attendeeName)
	{		
		boolean result = false;
		Waiters.WaitByXPath(spnAttendee,driver);		
		result = (spanAttendee.isDisplayed() && spanAttendee.getText().equals(attendeeName))? true : false;
		return result;
	} 
	
	public List<WebElement> attendeeRemoved(){
		List<WebElement> attendees = driver.findElements(By.xpath(spnAttendee));				
		return attendees;
	}
	
	public boolean checkTime(){		
		boolean result = false;
		String currentTime = RoomManagerTime.currenTime();
		Waiters.WaitByXPath(spnTime, driver);
		result = spanTime.getText().equals(currentTime)?true:false;
		return result;
	}	
	
	public boolean checkIfIconRedirectsToSchedulePage(){
		boolean result = false;
		Waiters.WaitByXPath(spnScheduleTitle, driver);		
		result = (spanSchedulePageTitle.getText().equals("Schedule") && driver.getCurrentUrl().equals(url+"/tablet/#/schedule"))?true:false;
		return result;
	}
	
	/**
	 * @return the text associated to the Schedule page.
	 */
	public String getScheduleLabelText(){
		Waiters.WaitByXPath("//span[text() = 'Schedule']",driver);
		element = driver.findElement(By.xpath("//span[text() = 'Schedule']"));

		return element.getText();
	}
	
	
	
	public MeetingsPage setStartTime(String begin){
		Waiters.WaitByXPath("//input[@type='time']",driver);
		element = driver.findElement(By.xpath("//input[@type='time']"));
		element.clear();
		element.sendKeys(begin);		
		return this;
	}
	public MeetingsPage setEndTime(String end){
		Waiters.WaitByXPath("(//input[@type='time'])[2]",driver);
		element = driver.findElement(By.xpath("(//input[@type='time'])[2]"));
		element.clear();
		element.sendKeys(end);		
		return this;
	}
	public MeetingsPage selectMeeting(String subjectMeeting){
		Waiters.WaitByXPath("//span[contains(.,'"+subjectMeeting+"')]",driver);
		element = driver.findElement(By.xpath("//span[contains(.,'"+subjectMeeting+"')]"));
		element.click();
		return this;
		
	}
		public boolean verifyErrorSubjectMessage(){
		Waiters.WaitByXPath(divErrMsSubject,driver);
		return (divErrMsjSubject.isDisplayed());
	} 
	
	public boolean verifyErrorOrganizerMessage(){
		Waiters.WaitByXPath(divErrMsOrganizer,driver);
		return (divErrMsjOrganizer.isDisplayed());			
	}
	
	public boolean verifyErrorFromHigherThanToMessage(){
		Waiters.WaitByXPath(divErrMsStartTime,driver);
		return (divErrMsjStartTime.isDisplayed());			
	}
	
	public String verifyCurrentDateInFromField(){
		Waiters.WaitByXPath(txtboxStartDate,driver);
		String fromValue = tbStartDate.getAttribute("value");
		return fromValue;	
	}
	public String verifyCurrentDateInToField(){
		Waiters.WaitByXPath(txtboxEndDate,driver);
		String toValue = tbEndDate.getAttribute("value");	
		return toValue;

	}
	public boolean verifyMeetingWasUpdated(){
		Waiters.WaitByXPath(divConfirmUpdateMs,driver);
		return (divConfirmUpdateMsj.isDisplayed());
	} 
}
