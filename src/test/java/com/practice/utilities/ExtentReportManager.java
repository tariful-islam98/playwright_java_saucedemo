package com.practice.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ExtentReportManager {
    private static final Logger logger = LogManager.getLogger(ExtentReportManager.class);
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize the Extent Report with timestamped report file
    public static void initReport() {
        try {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = "test-output/ExtentReport_" + timeStamp + ".html";
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);

            // Configure the HTML reporter
            htmlReporter.config().setDocumentTitle("Automation Report");
            htmlReporter.config().setReportName("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            logger.info("Extent report initialized successfully at: {}", reportPath);
        } catch (Exception e) {
            logger.error("Error initializing Extent Report", e);
        }
    }

    // Create a new test in the report
    public static void createTest(String testName) {
        try {
            test.set(extent.createTest(testName));
            logger.info("Test '{}' created in Extent report", testName);
        } catch (Exception e) {
            logger.error("Failed to create test in Extent report", e);
        }
    }

    // Log pass status for a test
    public static void logPass(String message) {
        try {
            if (test.get() != null) {
                test.get().pass(message);
                logger.info("Logged pass message: {}", message);
            } else {
                logger.warn("No test context available to log pass message.");
            }
        } catch (Exception e) {
            logger.error("Error logging pass message", e);
        }
    }

    // Log fail status for a test
    public static void logFail(String message) {
        try {
            if (test.get() != null) {
                test.get().fail(message);
                logger.info("Logged fail message: {}", message);
            } else {
                logger.warn("No test context available to log fail message.");
            }
        } catch (Exception e) {
            logger.error("Error logging fail message", e);
        }
    }

    // Add screenshot to the report (when test fails)
    public static void addScreenshot(byte[] screenshot) {
        try {
            if (screenshot != null && screenshot.length > 0) {
                String base64String = Base64.getEncoder().encodeToString(screenshot);
                test.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
                logger.info("Screenshot added to the report.");
            } else {
                logger.warn("Screenshot is null or empty. No screenshot added.");
            }
        } catch (Exception e) {
            logger.error("Error adding screenshot to the report", e);
        }
    }

    // Flush the report and save the results
    public static void flushReport() {
        try {
            if (extent != null) {
                extent.flush();
                logger.info("Extent report flushed and saved successfully.");
            } else {
                logger.warn("Extent report is null, nothing to flush.");
            }
        } catch (Exception e) {
            logger.error("Error flushing Extent report", e);
        }
    }
}
