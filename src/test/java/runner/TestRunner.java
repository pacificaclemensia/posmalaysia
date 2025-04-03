package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


	
@RunWith(Cucumber.class)
@CucumberOptions( 
		features = "src/test/resources/features",
		glue = "stepdefinitions", 
	    plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	        "pretty", 
	        "html:target/cucumber-reports.html",  
	        "json:target/cucumber.json",        
	        
	    },
	    monochrome = true 
	)
	
	public class TestRunner {

}
