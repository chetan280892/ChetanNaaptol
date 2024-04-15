package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

	
	@FindBy(xpath="//a[text()='Logout']")private WebElement logout ;
	@FindBy(xpath="//span[text()='Guest']")private WebElement hiGuest;
	
	public LogoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnLogoutButton() {
		logout.click();
	}
	public String afterLogoutMsg() {
		return hiGuest.getText();
	}
	
}
