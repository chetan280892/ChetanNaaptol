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
import utility.Reports;
@Listeners(test.Listeners.class)
public class RegisterAndLoginPageTest extends BaseTest{
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
	public void loginWithBlankMobileNumber(){
		test=reports.createTest("loginWithBlankMobileNumber");
		RegisterAndLoginPage registerPage=new RegisterAndLoginPage(driver);
		registerPage.clickRegisterLink();
		registerPage.clickOnContinue();
	    String errorMsg=registerPage.getInvalidMobileNumberMsg();
	    Assert.assertTrue(errorMsg.contains("Please enter a valid mobile number"));
}
	@Test
	public void loginWithInvalidMobileNumber() throws InterruptedException {
		test=reports.createTest("loginWithInvalidMobileNumber");
		RegisterAndLoginPage registerPage=new RegisterAndLoginPage(driver);
		registerPage.clickRegisterLink();
		registerPage.invalidMobileNumber();
		registerPage.clickOnContinue();
	    String errorMsg=registerPage.getInvalidMobileNumberMsg();
	    Assert.assertTrue(errorMsg.contains("Please enter a valid mobile number"));
}	
	@Test
	public void loginWithBlankOTPFunctionality(){
		test=reports.createTest("loginWithBlankOTPFunctionality");
		RegisterAndLoginPage registerPage=new RegisterAndLoginPage(driver);
		registerPage.clickRegisterLink();
		registerPage.enterMobileNumber();
		registerPage.clickOnContinue();
		registerPage.clickOnSubmit();
	    String errorMsg=registerPage.submitBlankOTPMsg();
	    Assert.assertTrue(errorMsg.contains("Please enter valid One Time Password (OTP)."));
}
	@Test
	public void loginWithInvalidOTPFunctionality() throws InterruptedException {
		test=reports.createTest("loginWithInvalidOTPFunctionality");
		RegisterAndLoginPage registerPage=new RegisterAndLoginPage(driver);
		registerPage.clickRegisterLink();
		registerPage.enterMobileNumber();
		registerPage.clickOnContinue();
		//ENTER OTP
		Thread.sleep(15000);
		registerPage.clickOnSubmit();
	    String errorMsg=registerPage.getInvalidOTPMsg();
	    Assert.assertTrue(errorMsg.contains("Incorrect One Time Password (OTP)"));
}

	@Test
	public void verifyChangeMobileNo() throws InterruptedException {
		test=reports.createTest("verifyChangeMobileNo");
		RegisterAndLoginPage registerPage=new RegisterAndLoginPage(driver);
		registerPage.clickRegisterLink();
		registerPage.enterMobileNumber();
		registerPage.clickOnContinue();
		Thread.sleep(2000);
		registerPage.clickOnChangeNumber();
	    String changeMobile=registerPage.getHelpText();
	    System.out.println(changeMobile);
	    Assert.assertTrue(changeMobile.contains("Help :"));
}
	@Test
	public void registerAccountFunctionality() throws InterruptedException {
		test=reports.createTest("registerAccountFunctionality");
		RegisterAndLoginPage registerPage=new RegisterAndLoginPage(driver);
		registerPage.clickRegisterLink();
		registerPage.enterMobileNumber();
		registerPage.clickOnContinue();
		//ENTER OTP
		Thread.sleep(15000);
		registerPage.clickOnSubmit();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement verifyHomePage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='User' ]")));
		String home=registerPage.verifyHome();
		Assert.assertTrue(home.contains("User"));
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