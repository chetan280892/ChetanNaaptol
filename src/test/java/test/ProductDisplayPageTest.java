package test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.Browser;
import pom.ProductDisplayPage;
import pom.RegisterAndLoginPage;
import pom.SearchPage;
import utility.Reports;
@Listeners(test.Listeners.class)
public class ProductDisplayPageTest extends BaseTest {
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
	public void verifyAddToWishlistWithoutLogin(){
		test=reports.createTest("verifyAddToWishlistWithoutLogin");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("Cooker");
		searchPage.clickSearchButton();
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		productDisplayPage.clickOnAddToWishList(2);
		String login=productDisplayPage.loginPage();
		Assert.assertTrue(login.contains("LOGIN / REGISTER"));
		
	}
	@Test
	public void verifyAddToWishlistWithLogin() throws InterruptedException{
		test=reports.createTest("verifyAddToWishlistWithLogin");
	RegisterAndLoginPage registerPage=new RegisterAndLoginPage(driver);
	registerPage.clickRegisterLink();
	registerPage.enterMobileNumber();
	registerPage.clickOnContinue();
	//ENTER OTP
	Thread.sleep(15000);
	registerPage.clickOnSubmit();
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	WebElement verifyHomePage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='User' ]")));
	SearchPage searchPage=new SearchPage(driver);
	searchPage.enterProduct("Cooker");
	searchPage.clickSearchButton();
	ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
	productDisplayPage.clickOnAddToWishList(3);
	WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(5));
	WebElement verifyHomePage1=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Product Added Successfully in Wishlist.']")));
	String msg=verifyHomePage1.getText();
	Assert.assertTrue(msg.contains("Product Added Successfully in Wishlist."));
}
	@Test
	public void verifyTermsAndConditionLink() throws InterruptedException{
		test=reports.createTest("verifyTermsAndConditionLink");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("Cooker");
		searchPage.clickSearchButton();
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		productDisplayPage.clickOnProduct(2);
		productDisplayPage.clickOnTermsAndCondition();
	    String url=driver.getCurrentUrl();
	    Assert.assertTrue(url.contains("terms-conditions/terms-of-use.html"));
	}

	@Test
	public void verifyProductAddedToCart() throws InterruptedException{
		test=reports.createTest("verifyProductAddedToCart");
	RegisterAndLoginPage registerPage=new RegisterAndLoginPage(driver);
	registerPage.clickRegisterLink();
	registerPage.enterMobileNumber();
	registerPage.clickOnContinue();
	//ENTER OTP
	Thread.sleep(15000);
	registerPage.clickOnSubmit();
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	WebElement verifyHomePage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='User' ]")));
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
	System.out.println(msg);
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
