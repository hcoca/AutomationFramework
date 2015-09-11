package org.rm.automation.tablet.tests.meetings;

import java.lang.String;

import org.rm.automation.tablet.pageobjects.BeginTest;
import org.testng.annotations.*;

public class meetingsTests {

	@Test
	public void createMeeting()
	{
		String url = "http://172.20.208.105:4040";
		String username = "atxrm\\elver";
		String password = "Control123";
		String organizer = "Administrator";
		String subject = "PageObjects Meeting";
		String atendees = "elver@atxrm.com";
		String beginTime = "12:45";
		String endTime = "13:45";

		//Optional variable of the function setBody();
		//String body = "It's optional";
		
		BeginTest.getLogin()
		.setUrl(url)
		.setUsername(username)
		.setPassword(password)
		.login()
		.setRoom()
		.access()
		.selectSchedulePage()
		.setOrganizer(organizer)
		.setSubject(subject)
		.setDates(beginTime, endTime)
		.setAtendees(atendees)		
		.confirmMeeting()
		.confirmUser(password)
		.saveMeeting()
		.meetingIsCreated(subject);		
		
	}
	
	@Test
	public void updateMeeting()
	{
		String url = "http://172.20.208.105:4040";
		String username = "atxrm\\elver";
		String password = "Control123";		
		String subject = "PageObjects Meeting UPDATED";
		String atendees = "susana@atxrm.com";
		String beginTime = "13:45";
		String endTime = "14:45";

		///Optional variable of the function setBody();
		String body = "Now I have a body :D";
		
		BeginTest.getLogin()
		.setUrl(url)
		.setUsername(username)
		.setPassword(password)
		.login()
		.setRoom()
		.access()
		.selectSchedulePage()
		.selectMeeting()		
		.setSubject(subject)
		.setDates(beginTime, endTime)
		.setAtendees(atendees)	
		.setBody(body)
		.updateMeeting()
		.confirmUser(password)
		.saveMeeting()
		.meetingIsCreated(subject);		
		
	}
	
	@Test
	public void deleteMeeting(){
		
		String url = "http://172.20.208.105:4040";
		String username = "atxrm\\elver";
		String password = "Control123";		
		
		BeginTest.getLogin()
		.setUrl(url)
		.setUsername(username)
		.setPassword(password)
		.login()
		.setRoom()
		.access()
		.selectSchedulePage()
		.selectMeeting()		
		.deleteMeeting()
		.confirmUser(password)
		.saveDeleteMeeting();		
	}
}
