package pom;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDisplayPage {

	@FindBy(xpath="//b[@class='wish2']")private WebElement addToWishList;
	@FindBy(xpath="//p[text()='Login / Register']")private WebElement loginPage;
	@FindBy(xpath="//a[@href='/terms-conditions/terms-of-use.html']")private WebElement termsAndCondition;
	@FindBy(xpath="//div[contains(@id,'productItem')]")private WebElement selectProduct;
	@FindBy(xpath="//span[text()='Click here to Buy']")private WebElement clickToBuy;
	@FindBy(xpath="//ul[@id='shopCartHead']")private WebElement addToCartMsg;
	
	public ProductDisplayPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public int clickOnAddToWishList(int index) {
		addToWishList.click();
		return index;
	}
	public void clickOnProduct(int index) {
		selectProduct.click();
	}
	public String loginPage() {
	return	loginPage.getText();
	
}
	public String addToCartMsg() {
		return	addToCartMsg.getText();
		
	}
	public void clickOnTermsAndCondition() {
		termsAndCondition.click();
	}
	public void clickOnToBuy() {
		clickToBuy.click();
	}
}