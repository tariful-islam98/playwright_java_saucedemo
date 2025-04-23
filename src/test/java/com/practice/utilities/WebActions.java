package com.practice.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;

public class WebActions {
    private static final Logger logger = LogManager.getLogger(WebActions.class);
    public Page page;

    public WebActions(Page page) {
        this.page = page;
    }

    public void click(String selector) {
        page.waitForSelector(selector);
        page.click(selector);
        logger.info("Clicked on element with selector: {}", selector);
    }

    public void type(String selector, String text) {
        logger.info("Attempting to type text into element with selector: {}", selector);
        page.waitForSelector(selector);
        page.fill(selector, text);
        logger.info("Typed text into element with selector: {}", selector);
    }

    public void scrollToElement(String selector) {
        logger.info("Attempting to scroll to element with selector: {}", selector);
        page.evaluate("selector => document.querySelector(selector).scrollIntoView()", selector);
    }

    public void waitForElementVisible(String selector, int timeout) {
        logger.info("Waiting for element to be visible with selector: {} and timeout: {} ms", selector, timeout);
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setTimeout(timeout)
                .setState(WaitForSelectorState.VISIBLE));
        logger.info("Element with selector '{}' is visible", selector);
    }

    public String getTextContent(String selector) {
        String textContent = page.textContent(selector);
        logger.info("Retrieved Text content '{}' for element with selector '{}'.", textContent, selector);
        return textContent;
    }

    public Locator getElement(String selector) {
        logger.info("Getting element with selector: {}", selector);
        return page.locator(selector);
    }

    public void takeScreenshot(String testName) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/" + testName + ".png")));
        logger.info("Screenshot saved for test: {}", testName);
    }
}
