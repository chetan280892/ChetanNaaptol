package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	public static ExtentReports createReports() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentreports.html");	
		ExtentReports reports = new ExtentReports();
			
			reports.attachReporter(sparkReporter);
		reports.setSystemInfo("Testing", "regression");
			reports.setSystemInfo("created By", "Chetan");
			return reports;
	}

	}
