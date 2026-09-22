package com.melihpektas.mobile.support;

import com.melihpektas.mobile.driver.DriverFactory;
import com.melihpektas.mobile.pages.CatalogPage;
import com.melihpektas.mobile.pages.HeaderBar;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/** Starts a clean app session per test and captures a screenshot when a test fails. */
public abstract class BaseTest {

    protected AndroidDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void startApp() {
        driver = DriverFactory.create();
    }

    @AfterMethod(alwaysRun = true)
    public void stopApp(ITestResult result) {
        if (driver == null) {
            return;
        }
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                Screenshots.save(driver, result.getTestClass().getRealClass().getSimpleName() + "-" + result.getName());
            }
        } finally {
            driver.quit();
            driver = null;
        }
    }

    protected CatalogPage catalog() {
        return new CatalogPage(driver).waitUntilLoaded();
    }

    protected HeaderBar header() {
        return new HeaderBar(driver);
    }
}
