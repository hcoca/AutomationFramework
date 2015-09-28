package org.rm.automation.admin.tests.tablets;


import java.util.Properties;

import org.rm.automation.admin.pageobjects.HomePage;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.admin.pageobjects.tablets.AppearanceTabletPage;
import org.rm.automation.admin.pageobjects.tablets.TabletsAdminPage;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ChangeColorTablet extends TestBaseSetup{
	
	private LoginPage login;
	private HomePage homePage;
	private TabletsAdminPage tabletPage;
	private AppearanceTabletPage appereaceTP;

	// room properties 
	private Properties settings = ReadPropertyValues
			.getPropertyFile("./Config/settings.properties");
	private String userName = settings.getProperty("username");
	private String password = settings.getProperty("password");
	
	private String[] colors = {"blue","green","orange","purple","red"} ;
	int randomnum = (int)(Math.random()*5);
	
	
	@Test
	public void testChangeColorTable()
	{
		login = new LoginPage(driver);
		homePage = login.SignIn(userName, password);
		tabletPage = homePage.SelectTabletsOptions();
		appereaceTP = tabletPage.appearenceTabletButton();
		appereaceTP.selectColorAndSave(colors[randomnum]);
		String cac =appereaceTP.IssameColor();
		Assert.assertEquals(cac, colors[randomnum]);
	}
}
