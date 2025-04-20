package com.practice.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practice.utilities.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        // Start the test and log the name of the test in the Extent Report
        ExtentReportManager.createTest(result.getName());
        ExtentReportManager.logPass("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test success and the corresponding message in the Extent Report
        ExtentReportManager.logPass("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log test failure and capture screenshot if available
        ExtentReportManager.logFail("Test failed: " + result.getName());

        // Optionally capture a screenshot on failure (this is just a placeholder)
        // byte[] screenshot = someMethodToCaptureScreenshot();
        // ExtentReportManager.addScreenshot(screenshot);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test skipped
        ExtentReportManager.logFail("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // This method will be called if a test fails but is within a success percentage
        ExtentReportManager.logFail("Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        // Called before the suite or test starts
        ExtentReportManager.initReport();
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        // Called after the suite or test finishes
        ExtentReportManager.flushReport();
    }
}
