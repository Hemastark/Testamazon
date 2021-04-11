package runner;


import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.cucumber.listener.Reporter;

import baseSetup.Operations;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src//test//java//features",
                            glue = "stepDefinitions",
                            tags = {"@Amazon"},
                            plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
                            monochrome = true
                 )

@Test
public class runner extends AbstractTestNGCucumberTests{
	
	@AfterClass
	public static void extentReport() throws IOException {
		Reporter.loadXMLConfig(Operations.filePath +"/extent-config.xml");
	}
	
}