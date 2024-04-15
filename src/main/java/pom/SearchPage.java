package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	@FindBy(xpath="//input[@id='header_search_text']")private WebElement searchText ;
	@FindBy(xpath="(//a[@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement searchButton;
	@FindBy(xpath="//div[@class='errorMsg']")private WebElement noProductMsg;
	@FindBy(xpath="//div[contains(@id,'productItem')]")private List<WebElement> product;
	@FindBy(xpath="//div[contains(@id,'productItem')]")private WebElement productName;
	
	public SearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterProduct(String name) {
		searchText.sendKeys(name);
	}
	public void clickSearchButton() {
		searchButton.click();
	}
	public String getNoProductMsg() {
		return noProductMsg.getText();
		}
	public int getNumberOfProductDisplayed() {
		return product.size();
		}
	public String getProductName() {
		return productName.getText();
		}
}
