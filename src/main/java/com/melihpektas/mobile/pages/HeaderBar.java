package com.melihpektas.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/** Top app bar that is visible on every main screen: menu, cart and cart badge. */
public class HeaderBar extends BasePage {

    private final By menuButton = id("menuIV");
    private final By cartButton = id("cartRL");
    private final By cartBadge = id("cartTV");

    public HeaderBar(AndroidDriver driver) {
        super(driver);
    }

    public LoginPage openLoginFromMenu() {
        tap(menuButton);
        tap(text("Log In"));
        return new LoginPage(driver);
    }

    public CartPage openCart() {
        tap(cartButton);
        return new CartPage(driver);
    }

    public boolean hasCartCount(String expected) {
        return waitForText(cartBadge, expected);
    }
}
