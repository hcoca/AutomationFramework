package org.rm.automation.admin.tests.impersonation;
import java.util.Properties;
import org.rm.automation.admin.pageobjects.LoginPage;
import org.rm.automation.utils.LogManager;
import org.rm.automation.utils.ReadPropertyValues;
import org.rm.automation.utils.TestBaseSetup;
import org.rm.automation.utils.api.ImpersonationRequests;
import org.testng.annotations.*;

public class EnableImpersonation extends TestBaseSetup {
	Properties settings = ReadPropertyValues.getPropertyFile("./Config/settings.properties");
	String username = settings.getProperty("username");
	String password = settings.getProperty("password");
	LoginPage loginPage;

	@BeforeMethod
	public void setImpersonationOff(){
		
		try{
			ImpersonationRequests.setImpersonationState(false);			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void enableImpersonation(){
		LogManager.info("EnableImpersonation: Executing Test Case");		
		loginPage = new LoginPage(driver)
		.SignIn(username, password)
		.SelectImpersonationOption()
		.clickImpersonationCheckbox()		
		.clickCredentialsRadioButton()
		.saveChanges()
		.impersonationIsEnabled()
		.SignOut();
	}
}

