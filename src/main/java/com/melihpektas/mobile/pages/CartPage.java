package com.melihpektas.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By itemTitles = id("titleTV");

    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    public List<String> itemNames() {
        visible(itemTitles);
        return driver.findElements(itemTitles).stream().map(WebElement::getText).toList();
    }

    /** A guest is sent to the login screen before checkout can start. */
    public LoginPage proceedToCheckoutAsGuest() {
        scrollTo("cartBt").click();
        return new LoginPage(driver);
    }
}
