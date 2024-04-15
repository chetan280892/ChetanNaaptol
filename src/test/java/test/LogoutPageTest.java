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
import pom.LogoutPage;
import pom.RegisterAndLoginPage;
import utility.Reports;
@Listeners(test.Listeners.class)
public class LogoutPageTest extends BaseTest{
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
	public void verifyLogoutFunctionality() throws InterruptedException {
		test=reports.createTest("verifyLogoutFunctionality");
		RegisterAndLoginPage registerPage=new RegisterAndLoginPage(driver);
		registerPage.clickRegisterLink();
		registerPage.enterMobileNumber();
		registerPage.clickOnContinue();
		//ENTER OTP
		Thread.sleep(15000);
		registerPage.clickOnSubmit();
		//Thread.sleep(5000);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement verifyHomePage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='User' ]")));
		LogoutPage logoutPage=new LogoutPage(driver);
		logoutPage.clickOnLogoutButton();
		String home=logoutPage.afterLogoutMsg();
		Assert.assertTrue(home.contains("Guest"));
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
