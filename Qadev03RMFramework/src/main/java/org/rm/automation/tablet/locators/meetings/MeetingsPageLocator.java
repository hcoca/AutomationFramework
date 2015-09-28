package org.rm.automation.tablet.locators.meetings;

public class MeetingsPageLocator {
	public static final String txtboxOrganizer="txtOrganizer";
	public static final String txtboxSubject="txtSubject";
	public static final String txtboxAttendees="(//input[@type='text'])[3]";
	public static final String txtboxBody="txtBody";
	public static final String txtboxStart="//input[@type='time']";
	public static final String txtboxEnd="(//input[@type='time'])[2]";
	public static final String txtboxStartDate="//input[@ng-model='editable.from' and @type='date']";
	public static final String txtboxEndDate="//input[@ng-model='editable.to' and @type='date']";
	public static final String txtboxPassword="//input[@type='password']";
	public static final String bttnCreate="//button[@class='clean item item-btn']";
	public static final String bttnUpdate="//button[contains(.,'Update')]";
	public static final String bttnRemove="//button[contains(.,'Remove')]";	
	public static final String bttnSave="//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]";
	public static final String bttnSaveDelete="//html/body/div[2]/div/div/div[2]/rm-modal/div[2]/div/div/div/ng-transclude/div/div/div[4]/div/button[2]";
	public static final String dvMeeting="//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div/div";
	public static final String dvAdvice="//div[@class='ng-binding ng-scope']";
	public static final String dvRoomName="//html/body/div[2]/div/div/div[1]/div[2]/div[2]";
	public static final String icnRemoveAttendee="//i[contains(@ng-click,'remove($index)')]";
	public static final String spnAttendee="//span[@class='rm-tag-elem ng-binding']";
	public static final String spnTime="//span[@ng-bind='currentTime']";
	public static final String spnScheduleTitle="//span[contains(.,'Schedule')]";
	public static final String divErrMsSubject="//small[@ng-show='formErrors.title' and contains(.,'Subject is required')]";
	public static final String divErrMsOrganizer="//small[@ng-show='formErrors.organizer' and contains(.,'Organizer is required')]";
	public static final String divErrMsStartTime="//small[@ng-show='formErrors.intervalInvalid' and contains(.,'Start time must be smaller than end time')]";
	public static final String divConfirmUpdateMs="//div[contains(.,'Meeting successfully updated')]";
}
