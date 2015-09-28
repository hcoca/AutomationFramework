package org.rm.automation.admin.pageobjects.tablets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AppearanceTabletPage {
	
	WebDriver driver;
	Select Selectcolor ;

	@FindBy(id="palette-select")
	WebElement selectColorTalet;
	
	@FindBy(xpath="//button[@ng-click='save()']")
	WebElement buttonSave;
	
	
	
	public AppearanceTabletPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		Selectcolor = new Select(selectColorTalet) ;
	}
	
	public void selectcolorTablet(String colorTablet) {
		Selectcolor.selectByVisibleText(colorTablet);
	}
	public TabletsAdminPage saveColor() {
		buttonSave.click();
		return new TabletsAdminPage(driver);
	}
	public TabletsAdminPage selectColorAndSave(String colorTa)
	{
		selectcolorTablet(colorTa);
		return saveColor();
	}
	public String IssameColor() {
		WebElement option = Selectcolor.getFirstSelectedOption();
		
		return option.getText();
	}
}
