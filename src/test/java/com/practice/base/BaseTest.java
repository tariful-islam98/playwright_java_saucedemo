package com.practice.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.practice.utilities.ConfigReader;
import com.practice.utilities.ExtentReportManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected static Playwright playwright;
    protected static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    protected static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    @BeforeSuite
    public static void setupSuite() {
        playwright = Playwright.create();
        ExtentReportManager.initReport();
    }

    @BeforeMethod
    public void setupTest() {
        String browserName = ConfigReader.getProperty("browser.name", "chromium").toLowerCase();
        Browser browser = createBrowserInstance(browserName);
        browserThreadLocal.set(browser);
        pageThreadLocal.set(browser.newPage());
    }

    @AfterMethod
    public void tearDownTest() {
        Browser browser = browserThreadLocal.get();
        if (browser != null) {
            browser.close();
            browserThreadLocal.remove();
        }
        pageThreadLocal.remove();
    }

    @AfterSuite
    public static void tearDownSuite() {
        if (playwright != null) {
            playwright.close();
        }
        ExtentReportManager.flushReport();
    }

    // Helper methods to get current instances
    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    public static Page getPage() {
        return pageThreadLocal.get();
    }

    private Browser createBrowserInstance(String browserName) {
        BrowserType browserType = switch (browserName) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium(); // default to chromium
        };

        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("browser.headless", "false")
        );

        return browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(headless));
        //.setSlowMo(0));
    }

}
