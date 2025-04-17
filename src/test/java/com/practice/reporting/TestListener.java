package com.practice.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.practice.utilities.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentReportManager.getExtent().createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed");
        // Add screenshot if needed
         extentTest.get().addScreenCaptureFromPath("screenshot.png");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
        extentTest.remove();
    }
}
