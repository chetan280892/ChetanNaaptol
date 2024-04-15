package pom;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterAndLoginPage {

	@FindBy(xpath="//a[@id='login-panel-link']")private WebElement register;
	@FindBy(xpath="//input[@id='registration-basic-panel-mobile']")private WebElement mobileNumber;
	@FindBy(xpath="//input[@id='registration-basic-panel-mobile']")private WebElement invalidMobileNumber;
	@FindBy(xpath="//input[@id='registration-basic-panel-submit']")private WebElement submit;
	@FindBy(xpath="//b[text()='Help : ']")private WebElement help;
	@FindBy(xpath="//input[@id='registration-otp-panel-otp']")private WebElement otp;
	@FindBy(xpath="//input[@id='registration-otp-panel-submit']")private WebElement submitOTP;
	@FindBy(xpath="//b[text()='User' ]")private WebElement verifyHomePage;
	@FindBy(xpath="//span[@class='errorMsgLogin']")private WebElement incorrectOTP;
	@FindBy(xpath="//a[text()='change?']")private WebElement changeMobileNumber;
	@FindBy(xpath="//a[text()='resend?']")private WebElement resendOTP;
	@FindBy(xpath="//a[@title='Close']")private WebElement closeRegisterPage;
	@FindBy(xpath="//span[text()='Please enter valid One Time Password (OTP).']")private WebElement blankOTP;
	@FindBy(xpath="//span[text()='Please enter a valid mobile number']")private WebElement invalidMobileNumberMsg;
	
	public RegisterAndLoginPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	public void clickRegisterLink() {
	register.click();
	}
	public void enterMobileNumber() {
	mobileNumber.sendKeys("9993778876");
	}
	public void invalidMobileNumber() {
		invalidMobileNumber.sendKeys("999377");
		}
	public void clickOnContinue() {
	submit.click();
	}
	public void clickOnSubmit() {
	submitOTP.click();
	}
	public String verifyHome() {
	return verifyHomePage.getText();
	}
    public void clickOnChangeNumber() {
    	changeMobileNumber.click();
    }
    public String getInvalidOTPMsg() {
    return incorrectOTP.getText();
    }
    public String submitBlankOTPMsg() {
        return blankOTP.getText();
        }
    public String getInvalidMobileNumberMsg() {
        return invalidMobileNumberMsg.getText();
        }
    public String getHelpText() {
    	return help.getText();
        }
}