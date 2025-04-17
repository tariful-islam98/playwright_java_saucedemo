package com.practice.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"src.test.java.com.practice.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
