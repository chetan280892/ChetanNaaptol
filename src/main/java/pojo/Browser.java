package pojo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {

	public static WebDriver launchBrowser(String browser)
	{
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
           WebDriverManager.edgedriver().setup();
           driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get("https://www.naaptol.com/");
	return driver;
	}
}