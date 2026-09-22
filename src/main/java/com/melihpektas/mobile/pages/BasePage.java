package com.melihpektas.mobile.pages;

import com.melihpektas.mobile.driver.Config;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

/** Shared, wait-aware interactions. Page objects never sleep; they wait for a condition. */
public abstract class BasePage {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);

    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    protected static By id(String resourceName) {
        return AppiumBy.id(Config.APP_PACKAGE + ":id/" + resourceName);
    }

    protected static By text(String visibleText) {
        return AppiumBy.androidUIAutomator("new UiSelector().text(\"" + visibleText + "\")");
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void tap(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected String textOf(By locator) {
        return visible(locator).getText();
    }

    protected boolean waitForText(By locator, String expected) {
        try {
            return wait.until(ExpectedConditions.textToBe(locator, expected));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /** Scrolls the screen's scrollable container until the element is on screen. */
    protected WebElement scrollTo(String resourceName) {
        String selector = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().resourceId(\"" + Config.APP_PACKAGE + ":id/" + resourceName + "\"))";
        try {
            driver.findElement(AppiumBy.androidUIAutomator(selector));
        } catch (NoSuchElementException e) {
            // Nothing to scroll on this screen; fall through to a normal wait.
        }
        return visible(id(resourceName));
    }

    protected void typeInto(String resourceName, String value) {
        WebElement field = scrollTo(resourceName);
        field.clear();
        field.sendKeys(value);
        hideKeyboard();
    }

    protected void hideKeyboard() {
        try {
            driver.executeScript("mobile: hideKeyboard", Map.of());
        } catch (WebDriverException e) {
            // Keyboard was not open.
        }
    }
}
