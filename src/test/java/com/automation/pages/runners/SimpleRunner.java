package com.automation.pages.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/resources/features/firebase2.feature"},
        glue= {"com.automation.stepDefinitions"},
        dryRun=false,
        monochrome = true,
       // tags= "@smoke or @regression",
        plugin = {"pretty",
        		  "json:target/cucumber-reports/cucumber.json",
        		  "html:target/cucumber-reports/cucumberreport.html"}
        
        )
public class SimpleRunner extends AbstractTestNGCucumberTests{
	
	
	

}
