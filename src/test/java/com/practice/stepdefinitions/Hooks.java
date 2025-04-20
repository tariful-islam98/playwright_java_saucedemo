package com.practice.stepdefinitions;

import com.microsoft.playwright.Page;
import com.practice.base.BaseTest;
import com.practice.utilities.ExtentReportManager;
import io.cucumber.java.*;

import java.nio.file.Paths;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {
        BaseTest.initializeBrowser();
        ExtentReportManager.initReport();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.createTest(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            captureScreenshot();
            ExtentReportManager.logFail("Scenario failed");
        }
        ExtentReportManager.flushReport();
    }

    @AfterAll
    public static void afterAll() {
        BaseTest.closeBrowser();
    }

    private void captureScreenshot() {
        try {
            byte[] screenshot = BaseTest.page.screenshot(new Page.ScreenshotOptions()
                    .setFullPage(true)
                    .setPath(Paths.get("test-output/screenshots/" + System.currentTimeMillis() + ".png")));

            ExtentReportManager.addScreenshot(screenshot);
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
