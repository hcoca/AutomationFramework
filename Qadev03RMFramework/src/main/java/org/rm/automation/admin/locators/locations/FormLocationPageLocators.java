package org.rm.automation.admin.locators.locations;

public class FormLocationPageLocators {
	public static final String infoLinkPath= "//a[contains(.,'Location Info')]";
	public static final String associatedLinkPath= "//a[contains(.,'Location Associations')]";
	public static final String nameTextBoxId = "location-add-name";
	public static final String displayNameTextBoxId= "location-add-display-name";
	public static final String locationsListBtnPath= "//button[@ng-click='toggleTree()']";
	public static final String organizationListBtnPath= "//span[@ng-click='collapse($event)']";
	public static final String descriptionTextBoxId= "location-add-description";
	public static final String parentLocationTextBoxId = "location-add-parent-location" ;
	public static final String saveBtnPath= "//button[@ng-click='save()']";
	public static final String cancelBtnPath= "//button[@ng-click='cancel()']";
	public static final String locationsDivPath= "//div[contains(.,'aaa')]" ;
}
