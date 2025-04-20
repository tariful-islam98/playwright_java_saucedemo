package com.practice.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReport() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = "test-output/ExtentReport_" + timeStamp + ".html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Test Results");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void createTest(String testName) {
        test.set(extent.createTest(testName));
    }

    public static void logPass(String message) {
        test.get().pass(message);
    }

    public static void logFail(String message) {
        test.get().fail(message);
    }

    public static void addScreenshot(byte[] screenshot) {
        if (screenshot != null && screenshot.length > 0) {
            String base64String = Base64.getEncoder().encodeToString(screenshot);
            test.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
