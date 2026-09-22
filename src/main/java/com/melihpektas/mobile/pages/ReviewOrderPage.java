package com.melihpektas.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ReviewOrderPage extends BasePage {

    private final By heading = id("enterShippingAddressTV");

    public ReviewOrderPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isShown() {
        return waitForText(heading, "Review your order");
    }

    public CheckoutCompletePage placeOrder() {
        scrollTo("paymentBtn").click();
        return new CheckoutCompletePage(driver);
    }
}
