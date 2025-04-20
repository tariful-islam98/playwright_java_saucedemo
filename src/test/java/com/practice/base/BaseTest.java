package com.practice.base;

import com.microsoft.playwright.*;
import com.practice.utilities.ConfigReader;
import lombok.Getter;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    @Getter
    public static Page page;

    public static void initializeBrowser() {
        playwright = Playwright.create();
        browser = createBrowserInstance();
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        page = context.newPage();
    }

    public static void closeBrowser() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    private static Browser createBrowserInstance(){
        String browserType = ConfigReader.getProperty("browser.name", "chromium");
        return switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            case "webkit" -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            default -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        };
    }
}
