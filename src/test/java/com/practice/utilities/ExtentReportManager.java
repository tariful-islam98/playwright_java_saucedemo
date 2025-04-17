package com.practice.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.Getter;

public class ExtentReportManager {
    @Getter
    private static final ExtentReports extent = new ExtentReports();

    public static void initReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent.attachReporter(spark);
    }

    public static void flushReport() {
        extent.flush();
    }
}
