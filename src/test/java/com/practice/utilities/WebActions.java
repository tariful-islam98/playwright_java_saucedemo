package com.practice.utilities;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;

public class WebActions {
    protected Page page;

    public WebActions(Page page) {
        this.page = page;
    }

    public void click(String selector) {
        page.waitForSelector(selector);
        page.click(selector);
    }

    public void type(String selector, String text) {
        page.waitForSelector(selector);
        page.fill(selector, text);
    }

    public void scrollToElement(String selector) {
        page.evaluate("selector => document.querySelector(selector).scrollIntoView()", selector);
    }

    public void waitForElementVisible(String selector, int timeout) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setTimeout(timeout)
                .setState(WaitForSelectorState.VISIBLE));
    }

    public String getTextContent(String selector) {
        return page.textContent(selector);
    }

    public void takeScreenshot(String testName) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/" + testName + ".png")));
    }
}
