package org.rm.automation.tablet.tests.search;

import org.rm.automation.tablet.pageobjects.BeginTest;
import org.testng.annotations.*;

public class searchTests {
	@Test
	public void searchRoomByResource(){
		String roomName = "0B3";
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
		.selectSearchPage()
		.selectResource()
		.selectRoom(roomName)
		.isTheRightRoom(roomName);				
	}
	
	@Test
	public void searchRoomByName(){
		String roomName = "Morochata";
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
		.selectSearchPage()
		.enableAdvancedSearch()
		.typeRoomName(roomName)
		.selectRoom(roomName)
		.isTheRightRoom(roomName);				
	}
}
