package com.practice.base;

import com.microsoft.playwright.*;
import com.practice.utilities.ConfigReader;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
    // Logger instance for logging events
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    @Getter
    public static Page page;

    // Initialize the browser and context
    public static void initializeBrowser() {
        logger.info("Initializing Playwright...");
        playwright = Playwright.create();

        logger.info("Creating browser instance...");
        browser = createBrowserInstance();

        int width = Integer.parseInt(ConfigReader.getProperty("browser.width", "1920"));
        int height = Integer.parseInt(ConfigReader.getProperty("browser.height", "1080"));
        logger.info("Setting browser viewport size to {}x{}", width, height);

        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(width, height));
        page = context.newPage();

        logger.info("Browser initialized successfully.");
    }

    // Close the browser and resources
    public static void closeBrowser() {
        logger.info("Closing the browser...");

        if (page != null) {
            page.close();
            logger.info("Page closed.");
        }

        if (context != null) {
            context.close();
            logger.info("Context closed.");
        }

        if (browser != null) {
            browser.close();
            logger.info("Browser closed.");
        }

        if (playwright != null) {
            playwright.close();
            logger.info("Playwright closed.");
        }

        logger.info("Browser resources cleaned up.");
    }

    // Helper method to create a browser instance
    private static Browser createBrowserInstance() {
        String browserType = ConfigReader.getProperty("browser.name", "chromium");
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("browser.headless", "false"));
        logger.info("Launching browser: {}", browserType);

        Browser browserInstance = switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
            case "webkit" -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
            default ->
                    playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless).setSlowMo(100));
        };

        logger.info("Browser launched successfully: {}", browserType);
        return browserInstance;
    }
}

