package test;

import java.time.Duration;

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
import pom.RegisterAndLoginPage;
import pom.SearchPage;
import utility.Reports;
@Listeners(test.Listeners.class)
public class SearchPageTest extends BaseTest {
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
	public void verifyNonExistingProductWithoutLogin(){
		test=reports.createTest("verifyNonExistingProductWithoutLogin");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("iphone");
		searchPage.clickSearchButton();
		String msg=searchPage.getNoProductMsg();
		Assert.assertTrue(msg.contains("no product matches"));
	}
	@Test
	public void verifyExistingProductWithoutLogin() throws InterruptedException{
		test=reports.createTest("verifyExistingProductWithoutLogin");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("Samsung Mobile");
		searchPage.clickSearchButton();
		int numberOfProduct=searchPage.getNumberOfProductDisplayed();
		Assert.assertTrue(numberOfProduct>0);
	}
	@Test
	public void verifyNonExistingProductWithLogin() throws InterruptedException{
		test=reports.createTest("verifyNonExistingProductWithLogin");
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
		searchPage.enterProduct("iphone");
		searchPage.clickSearchButton();
		String msg=searchPage.getNoProductMsg();
		Assert.assertTrue(msg.contains("no product matches"));
	}
	@Test
	public void verifyExistingProductWithLogin() throws InterruptedException{
		test=reports.createTest("verifyExistingProductWithLogin");
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
		searchPage.enterProduct("Samsung Mobile");
		searchPage.clickSearchButton();
		int numberOfProduct=searchPage.getNumberOfProductDisplayed();
		Assert.assertTrue(numberOfProduct>0);
		}
	@Test
	public void verifyMultipleProductNameFunctionality(){
		test=reports.createTest("verifyMultipleProductNameFunctionality");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("samsung, cooker, iphone, laptop");
		searchPage.clickSearchButton();
		String msg=searchPage.getNoProductMsg();
		Assert.assertTrue(msg.contains("no product matches"));
	}
	@Test
	public void verifyProductWithBrandName(){
		test=reports.createTest("verifyProductWithBrandName");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("Samsung");
		searchPage.clickSearchButton();
		String productName=searchPage.getProductName();
		Assert.assertTrue(productName.contains("Samsung"));
	}
	@Test
	public void verifyProductWithCategoryName() throws InterruptedException{
		test=reports.createTest("verifyProductWithCategoryName");
		SearchPage searchPage=new SearchPage(driver);
		searchPage.enterProduct("Mobile");
		searchPage.clickSearchButton();
		String productName=searchPage.getProductName();
		Assert.assertTrue(productName.contains("Mobile"));
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