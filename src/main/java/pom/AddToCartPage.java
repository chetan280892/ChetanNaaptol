package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage {
	
	@FindBy(xpath="//a[text()='Remove']")private List<WebElement> remove;
	@FindBy(xpath="//a[@title='Close']")private WebElement closeAddToCart;
	@FindBy(xpath="(//img[@title='Online Shopping in India'])[2]")private WebElement NaaptolLogo;
	@FindBy(xpath="//a[text()='Remove']")private WebElement removeButton;
	@FindBy(xpath="//ul[@class='tabs']")private WebElement paymentOption;
	@FindBy(xpath="//a[@class='red_button2']")private WebElement proceedToCheckout;
	@FindBy(xpath="//span[@class='offer-price']")private List<WebElement> productPriceOnPDP;
	@FindBy(xpath="//span[@class='offer-price']")private List<WebElement> productPriceOnCart;
	@FindBy(xpath="//a[text()='7 Pcs Induction Friendly Cookware Set + Free Knife Set']")private WebElement selectProductInCart;
	@FindBy(xpath="//span[text()='Quick View']")private WebElement quickView;
	@FindBy(xpath="//div[@class='grid_Square ']")private WebElement products;
	
	public AddToCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public int getRemoveButtonCount() {
		return remove.size();
	}
	public void closeAddToCartWindow() {
		closeAddToCart.click();
	}
	public void clickOnNaptolLogo() {
		NaaptolLogo.click();
	}
	public void clickOnRemoveButton() {
		removeButton.click();
	}
	public String getPaymentOptionMsg() {
		return paymentOption.getText();
	}
	public void clickOnProceedToCheckout() {
		proceedToCheckout.click();
	}
	public String getProductPriceOnPDP(int index) {
		return productPriceOnPDP.get(index).getText();
	}
	public String getProductPriceOnCart(int index) {
		String a=productPriceOnCart.get(index).getText();
		String b=a.substring(0, 5);
		return b;
	}
	public void clickSelectProductInCart() {
		selectProductInCart.click();
	}
	public void howeringOnProduct(WebDriver driver,int index) {
		Actions action=new Actions(driver);
		action.moveToElement(products).perform();
	}
	public void clickQuickView() {
		quickView.click();
		
	}
}
