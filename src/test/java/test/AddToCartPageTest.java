package test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.Browser;
import pom.AddToCartPage;
import pom.ProductDisplayPage;
import pom.RegisterAndLoginPage;
import pom.SearchPage;
import utility.Reports;
@Listeners(test.Listeners.class)
public class AddToCartPageTest extends BaseTest{
	ExtentReports reports;
	ExtentTest test;
	@BeforeTest
	public void setupReports() {
		reports=Reports.createReports();
	}
	@BeforeMethod
	@Parameters({"chrome"})
	public void launchApplication(String browser) {
		driver=Browser.launchBrowser(browser);	
	}
	@Test
	public void verifyProductAddedToCart() throws InterruptedException{
		test=reports.createTest("verifyProductAddedToCart");
	SearchPage searchPage=new SearchPage(driver);
	searchPage.enterProduct("Cooker");
	searchPage.clickSearchButton();
	ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
	WebElement clickOnProduct=driver.findElement(By.xpath("(//div[contains(@id,'productItem')])[3]"));
	clickOnProduct.click();
	Set<String> handles=driver.getWindowHandles();
	Iterator<String> i=handles.iterator();
	String handle=i.next();
	String handle1=i.next();
	driver.switchTo().window(handle1);
	productDisplayPage.clickOnToBuy();
	String msg=productDisplayPage.addToCartMsg();
	Assert.assertTrue(msg.contains("My Shopping Cart:"));
}
	@Test
	public void verifyProductCountAddedToCart() throws InterruptedException{
		test=reports.createTest("verifyProductCountAddedToCart");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("Cooker");
		searchPage.clickSearchButton();
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		WebElement clickOnProduct=driver.findElement(By.xpath("(//div[contains(@id,'productItem')])[2]"));
		clickOnProduct.click();
		switchToWindow("5 + 3 Ltr Pressure Cooker");
		productDisplayPage.clickOnToBuy();
		AddToCartPage addToCartPage=new AddToCartPage(driver);
		Thread.sleep(3000);
		addToCartPage.closeAddToCartWindow();
		addToCartPage.clickOnNaptolLogo();
		searchPage.enterProduct("Cooker");
		searchPage.clickSearchButton();
		WebElement clickOnProduct2=driver.findElement(By.xpath("(//div[contains(@id,'productItem')])[3]"));
		clickOnProduct2.click();
		switchToWindow("7 Pcs Induction");
		productDisplayPage.clickOnToBuy();
		int removeCount=addToCartPage.getRemoveButtonCount();
		Assert.assertEquals(removeCount,2);
	}
	@Test
	public void verifyRemoveproductFromCart() throws InterruptedException{
		test=reports.createTest("verifyRemoveproductFromCart");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("Cooker");
		searchPage.clickSearchButton();
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		WebElement clickOnProduct=driver.findElement(By.xpath("(//div[contains(@id,'productItem')])[2]"));
		clickOnProduct.click();
		switchToWindow("5 + 3 Ltr Pressure Cooker");
		productDisplayPage.clickOnToBuy();
		AddToCartPage addToCartPage=new AddToCartPage(driver);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement clickRemove=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Remove']")));
		clickRemove.click();
		Thread.sleep(3000);
		int removeCount=addToCartPage.getRemoveButtonCount();
		Assert.assertEquals(removeCount,0);
}
	@Test
	public void verifyProceedToCheckoutFunctinality() throws InterruptedException{
		test=reports.createTest("verifyProceedToCheckoutFunctinality");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("Cooker");
		searchPage.clickSearchButton();
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		WebElement clickOnProduct=driver.findElement(By.xpath("(//div[contains(@id,'productItem')])[2]"));
		clickOnProduct.click();
		switchToWindow("5 + 3 Ltr Pressure Cooker");
		productDisplayPage.clickOnToBuy();
		AddToCartPage addToCartPage=new AddToCartPage(driver);
		addToCartPage.clickOnProceedToCheckout();
		String msg=addToCartPage.getPaymentOptionMsg();
		Assert.assertTrue(msg.contains("Select Payment Option"));
}
	@Test
	public void verifyProceedToCheckoutFunctinalityWithoutLogin() throws InterruptedException{
		test=reports.createTest("verifyProceedToCheckoutFunctinalityWithoutLogin");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("Cooker");
		searchPage.clickSearchButton();
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		WebElement clickOnProduct=driver.findElement(By.xpath("(//div[contains(@id,'productItem')])[2]"));
		clickOnProduct.click();
		switchToWindow("5 + 3 Ltr Pressure Cooker");
		productDisplayPage.clickOnToBuy();
		AddToCartPage addToCartPage=new AddToCartPage(driver);
		addToCartPage.clickOnProceedToCheckout();
		String url=driver.getCurrentUrl();
		Assert.assertTrue(url.contains("guestuserlogin.html"));
}
	@Test
	public void verifyProductPriceInCart() throws InterruptedException{
		test=reports.createTest("verifyProductPriceInCart");
		SearchPage searchPage=new SearchPage(driver);
		AddToCartPage addToCartPage=new AddToCartPage(driver);
		searchPage.enterProduct("Cooker");
		searchPage.clickSearchButton();
		Thread.sleep(3000);
		String priceOnPDP=addToCartPage.getProductPriceOnPDP(2);
		WebElement clickOnProduct=driver.findElement(By.xpath("(//div[contains(@id,'productItem')])[2]"));
		clickOnProduct.click();
		switchToWindow("5 + 3 Ltr Pressure Cooker");
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		productDisplayPage.clickOnToBuy();
		String priceOnCart=addToCartPage.getProductPriceOnCart(0);
		Assert.assertEquals(priceOnPDP,priceOnCart);	
	}
	@Test
	public void verifyProductDetailLinkOnCartPage() throws InterruptedException{
		test=reports.createTest("verifyProductDetailLinkOnCartPage");
		SearchPage searchPage=new SearchPage(driver);
		AddToCartPage addToCartPage=new AddToCartPage(driver);
		searchPage.enterProduct("Cooker");
		searchPage.clickSearchButton();
		Thread.sleep(3000);
		WebElement clickOnProduct=driver.findElement(By.xpath("(//div[contains(@id,'productItem')])[3]"));
		clickOnProduct.click();
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		productDisplayPage.clickOnToBuy();
		Thread.sleep(3000);
		addToCartPage.clickSelectProductInCart();
		String url=driver.getCurrentUrl();
		Assert.assertTrue(url.contains("12453571.html"));
	}
	@Test
	public void verifyProductQuickView() throws InterruptedException{
		test=reports.createTest("verifyProductQuickView");
		SearchPage searchPage=new SearchPage(driver);
		AddToCartPage addToCartPage=new AddToCartPage(driver);
		searchPage.enterProduct("Mobile");
		searchPage.clickSearchButton();
		Thread.sleep(3000);
		addToCartPage.howeringOnProduct(driver,3);
		addToCartPage.clickQuickView();
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		Thread.sleep(3000);
		productDisplayPage.clickOnToBuy();
		Thread.sleep(3000);
		String msg=productDisplayPage.addToCartMsg();
		Assert.assertTrue(msg.contains("My Shopping Cart:"));
	}
	@AfterMethod
	public void getTestResult(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			
			test.log(Status.PASS,result.getName());
			
	}
	else if(result.getStatus()==ITestResult.FAILURE) {
		test.log(Status.FAIL,result.getName());
	}
	else if(result.getStatus()==ITestResult.SKIP) {
		test.log(Status.SKIP,result.getName());
	}
	}
	@AfterTest
	public void publishReports() {
	reports.flush();
	}
}

