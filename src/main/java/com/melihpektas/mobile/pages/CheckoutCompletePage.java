package com.melihpektas.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CheckoutCompletePage extends BasePage {

    private final By heading = id("completeTV");

    public CheckoutCompletePage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isShown() {
        return waitForText(heading, "Checkout Complete");
    }
}
